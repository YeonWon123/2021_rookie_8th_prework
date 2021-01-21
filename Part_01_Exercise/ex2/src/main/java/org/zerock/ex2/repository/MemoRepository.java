package org.zerock.ex2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.ex2.entity.Memo;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    // Memo 객체의 mno값이 70부터 80사이의 객체들을 구하고, mno의 역순으로 정렬하고 싶은 경우
    List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);

    // 쿼리 메서드와 Pageable 결합하기 - 좀 더 간단한 형태의 메서드 선언 가능
    Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);

    // deleteBy로 시작하는 삭제 처리
    void deleteMemoByMnoLessThan(Long num);
}
