package org.zerock.mreview.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mreview.entity.Member;

import java.util.stream.IntStream;

// 100명의 회원(Reviewer) 등록
@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMembers() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("r"+i+"@zerock.org")
                    .pw("1111")
                    .nickname("reviewer"+i).build();
            memberRepository.save(member);
        });
    }

    // 1번 회원이 작성한 리뷰를 모두 삭제하는 테스트 코드
    @Commit
    @Transactional
    @Test
    public void testDeleteMember() {
        Long mid = 1L; // Member의 mid

        Member member = Member.builder().mid(mid).build();

        // Error!
        // 1) FK(foreign key, 외래 키)를 가지는 Review쪽을 먼저 삭제하지 않음
        // 2) 트랜잭션 관련 처리가 없음
        // memberRepository.deleteById(mid);
        // reviewRepository.deleteByMember(member);

        // 순서 주의
        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(mid);
    }
}
