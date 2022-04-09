package com.koorung.book.freelecspringbootaws.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    /**
     * Spring Data JPA로 해결되지 않는 부분은 @Query 등을 사용하여 기능을 추가할 수 있음
     * (사실 Spring Data JPA만으로 거의 해결되긴 함..)
     * (+ QueryDsl을 사용하면 객체지향적으로 쿼리를 날릴 수 있어 매우 유용하다)
     */
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")  // jpql 사용..
    List<Posts> findAllDesc();
}
