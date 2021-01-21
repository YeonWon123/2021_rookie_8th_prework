package org.zerock.mreview.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mreview.entity.Member;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.Review;

import java.util.stream.IntStream;

// 100개의 영화(movie 테이블)와 100명의 회원(m_member)이 존재
// 이를 이용하여 1~5점까지의 평점과 리뷰 내용을 등록하는 테스트 코드
@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMovieReviews() {
        // 200개의 리뷰를 동록
        IntStream.rangeClosed(1, 200).forEach(i -> {
            // 영화 번호
            Long mno = (long)(Math.random() * 100) + 1;

            // 리뷰어 번호
            Long mid = ((long)(Math.random() * 100) + 1);
            Member member = Member.builder().mid(mid).build();

            Review movieReview = Review.builder()
                    .member(member)
                    .movie(Movie.builder().mno(mno).build())
                    .grade((int)(Math.random() * 5) + 1)
                    .text("이 영화에 대한 느낌..." + i)
                    .build();

            reviewRepository.save(movieReview);
        });
    }

}
