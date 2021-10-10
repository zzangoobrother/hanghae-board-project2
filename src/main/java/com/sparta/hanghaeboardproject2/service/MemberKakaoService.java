package com.sparta.hanghaeboardproject2.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.hanghaeboardproject2.dto.MemberKakaoDto;
import com.sparta.hanghaeboardproject2.model.Member;
import com.sparta.hanghaeboardproject2.repository.MemberRepository;
import com.sparta.hanghaeboardproject2.security.MemberDetailsImpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class MemberKakaoService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    
    public MemberKakaoService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public void memberKakaoLogin(String code) throws JsonProcessingException {
        // 1. "인가 코드" 엑세스 토큰 요청
        String accessToken = getAccessToken(code);

        // 2. 토큰으로 카카오 API 호출
        MemberKakaoDto memberKakaoDto = getKakaoUserInfo(accessToken);

        Member sameMember = memberRepository.findByEmail(memberKakaoDto.getEmail()).orElse(null);

        Member kakaoMember = null;
        if (sameMember == null) {
            // 3. 필요시 회원가입
            kakaoMember = registerKakaoUserIfNeeded(memberKakaoDto);
        } else {
            kakaoMember = updateKakaoUser(sameMember, memberKakaoDto);
        }

        // 4. 강제 로그인 처리
        forceLogin(kakaoMember);
    }

    private String getAccessToken(String code) throws JsonProcessingException {
        // HTTP header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "6a018c099db2b107f7770f7280aa8057");
        body.add("redirect_uri", "http://3.36.92.203:80/member/kakao/callback");
        body.add("code", code);

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // HTTP 응답
        String responseEntityBody = responseEntity.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseEntityBody);

        return jsonNode.get("access_token").asText();
    }

    private MemberKakaoDto getKakaoUserInfo(String accessToken) throws JsonProcessingException {
        // HTTP header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> memberKakaoRequest = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                memberKakaoRequest,
                String.class
        );

        String responseEntityBody = responseEntity.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseEntityBody);
        Long id = jsonNode.get("id").asLong();
        String nickname = jsonNode.get("properties").get("nickname").asText();
        String email = jsonNode.get("kakao_account").get("email").asText();

        return new MemberKakaoDto(id, nickname, email);

    }

    private Member registerKakaoUserIfNeeded(MemberKakaoDto memberKakaoDto) {
        Long kakaoId = memberKakaoDto.getId();
        Member kakaoMember = memberRepository.findByKakaoId(kakaoId).orElse(null);

        if (kakaoMember == null) {
            // 카카오 회원가입
            String nickname = memberKakaoDto.getNickname();

            String password = UUID.randomUUID().toString();
            String encoderPassword = passwordEncoder.encode(password);

            String email = memberKakaoDto.getEmail();

            kakaoMember = new Member(nickname, encoderPassword, email, kakaoId);
            kakaoMember = memberRepository.save(kakaoMember);
        }

        return kakaoMember;
    }

    private Member updateKakaoUser(Member member, MemberKakaoDto memberKakaoDto) {
        if (member.getKakaoId() == null) {
            member.setKakaoId(memberKakaoDto.getId());
            memberRepository.save(member);
        }

        return member;
    }

    private void forceLogin(Member kakaoMember) {
        MemberDetailsImpl memberDetails = new MemberDetailsImpl(kakaoMember);
        Authentication authentication = new UsernamePasswordAuthenticationToken(memberDetails, null, memberDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
