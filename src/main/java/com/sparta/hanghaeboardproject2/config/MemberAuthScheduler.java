package com.sparta.hanghaeboardproject2.config;

import com.sparta.hanghaeboardproject2.model.Member;
import com.sparta.hanghaeboardproject2.repository.MemberRepository;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MemberAuthScheduler {

  private final MemberRepository memberRepository;

  public MemberAuthScheduler(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

//  @Scheduled(cron = "15 * * * * *")
  public void authMemberEmailDelete() {
    System.out.println("member 삭제");
    List<Member> members = memberRepository.findAllByAuthCheckFalseOrderByCreatedAtDesc();

    for (Member member : members) {
      LocalDateTime createLocalDateTime = member.getCreatedAt();
      LocalDateTime now = LocalDateTime.now();

      long betweenSec = ChronoUnit.SECONDS.between(createLocalDateTime, now);
      if (betweenSec > 300) {
        memberRepository.deleteById(member.getId());
      }
    }

  }
}
