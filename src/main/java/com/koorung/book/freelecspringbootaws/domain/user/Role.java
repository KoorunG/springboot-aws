package com.koorung.book.freelecspringbootaws.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "손님"),  // 스프링 시큐리티에서는 권한코드 앞에 반드시 ROLE_ 이 와야 한다
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;

}
