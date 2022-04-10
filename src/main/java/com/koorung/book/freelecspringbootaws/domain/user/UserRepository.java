package com.koorung.book.freelecspringbootaws.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // findByEmail : 이미 스프링 데이터 JPA에서 이름 기반으로 생성해주기 때문에 만들지 않았다..
    Optional<User> findByEmail(String email);
}
