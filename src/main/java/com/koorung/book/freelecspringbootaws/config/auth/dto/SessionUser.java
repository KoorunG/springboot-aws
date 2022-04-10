package com.koorung.book.freelecspringbootaws.config.auth.dto;

import com.koorung.book.freelecspringbootaws.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

// 세션에 사용자를 저장하기 위한 DTO 클래스
// SessionUser에는 인증된 사용자 정보만 필요하기 때문에 name, email, picture 필드만 선언했다
// 직렬화를 위해 Serializable을 구현했다
@Getter
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
