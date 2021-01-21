package org.zerock.mreview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.mreview.entity.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    // 영화(Movie)와 리뷰(Review)를 이용해서 페이징 처리
    // @Query("select m, avg(coalesce(r.grade, 0)), count(distinct r) from Movie m " + "left outer join Review r on r.movie = m group by m")

    // 영화(Movie)와 리뷰(Review)를 이용해서 페이징 처리, 이 때 영화 이미지(MovieImage)도 같이 결합
    // N+1 문제 발생 (1번의 쿼리로 N개의 데이터를 가져옴, N개의 데이터를 처리하기 위해 필요한 추가적인 쿼리가 N개에 대해 수행되는 상황)
    // @Query("select m, max(mi), avg(coalesce(r.grade, 0)), count(distinct r) from Movie m " +
    //        "left outer join MovieImage mi on mi.movie = m " +
    //        "left outer join Review r on r.movie = m group by m")

    // 위 문제를 해결하기 위한 방법 : 중간의 이미지를 1개로 줄여서 처리 - 중간에 max() 처리를 없앰
    // 이 경우 중간에 반복적으로 실행되는 부분 없이, 목록을 구하는 쿼리와 개수를 구하는 쿼리만 실행 (mariadb에서 limit 구문 정상 동작)
    // Movie와 MovieImage는 가장 먼저 입력된 이미지 번호와 연결
    @Query("select m, mi, avg(coalesce(r.grade, 0)), count(distinct r) from Movie m " +
           "left outer join MovieImage mi on mi.movie = m " +
           "left outer join Review r on r.movie = m group by m")
    Page<Object[]> getListPage(Pageable pageable); // 페이지 처리

    // 영화 이미지 중에서 가장 나중에 추가된 이미지를 가져올 수 있을까?
    // @Query("select m, i, count(r) from Movie m left join MovieImage i on i.movie = m " +
    //        "and i.inum = (select max(i2.inum) from MovieImage i2 where i2.movie = m" +
    //        "left outer join Review r on r.movie = m group by m")
    // Page<Object[]> getListPage(Pageable pageable); // 페이지 처리

    // 검색과 같이 동적으로 처리해야 하는 경우, JPQL Query 적용
    /*
    QMovie movie = QMovie.movie;
    QMovie review = QReview.review;
    QMovieImage movieImage = QMovieImage.movieImage;

    JPQLQuery<Movie> jpqlQuery = from(movie);
    jpqlQuery.leftJoin(movieImage).on(movieImage.movie.eq(movie));
    jpqlQuery.leftJoin(review).on(review.movie.eq(movie));

    JPQLQuery<Tuple> tuple = jpqlQuery.select(movie, movieImage, review.grade.avg(), review.countDistinct());

    BooleanBuilder booleanBuilder = new BooleanBuilder();
    BooleanExpression expression = movie.mno.gt(0L);
     */

    // 특정 영화의 평균 평점/리뷰 개수를 확인하는 쿼리
    // 1. 영화(Movie)와 이미지(MovieImage)간 조인 후 처리
    // @Query("select m, mi from Movie m left outer join MovieImage mi on mi.movie = m where m.mno = :mno")

    // 2. 영화(Movie)와 이미지(MovieImage) 그리고 리뷰(Review)와 관련된 내용 처리(left join 사용)
    // 리뷰 점수 평균과 리뷰 개수 확인 가능
    @Query("select m, mi, avg(coalesce(r.grade, 0)), count(r)" +
           "from Movie m left outer join MovieImage mi on mi.movie = m " +
           "left outer join Review r on r.movie = m " +
           "where m.mno = :mno group by mi")
    List<Object[]> getMovieWithAll(Long mno); // 특정 영화 조회


}
