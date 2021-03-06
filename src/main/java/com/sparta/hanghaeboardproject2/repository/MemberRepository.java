package com.sparta.hanghaeboardproject2.repository;

import com.sparta.hanghaeboardproject2.model.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
    Optional<Member> findByEmail(String email);
    Optional<Member> findByKakaoId(Long kakaoId);
    List<Member> findAllByAuthCheckFalseOrderByCreatedAtDesc();

    Optional<Member> findAllByEmailAndAuthCheckTrue(String email);
}
