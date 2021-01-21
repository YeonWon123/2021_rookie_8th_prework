package org.zerock.mreview.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // @EntityGraph : 엔티티의 특정한 속성을 같이 로딩하도록 표시하는 어노테이션
    // JPA를 이용하는 경우, 연관 관계의 FETCH 속성값을 LAZY(지연 로딩)로 지정하는 것이 일반적
    // 한번에 Review 객체와 Member 객체를 조회할 수 없는 문제가 있음
    // @EntityGraph는 이런 상황에서 특정 기능을 수행할 때에만 EAGER 로딩(즉시 로딩)을 하도록 지정 가능
    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    // 특정 영화에 대한 영화 리뷰 확인하기
    List<Review> findByMovie(Movie movie);

}
