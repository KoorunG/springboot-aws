package com.koorung.book.freelecspringbootaws.config.auth.dto;

import com.koorung.book.freelecspringbootaws.domain.user.Role;
import com.koorung.book.freelecspringbootaws.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String email;
    private String name;
    private String pictures;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String email, String name, String pictures) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.email = email;
        this.name = name;
        this.pictures = pictures;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String)attributes.get("name"))
                .email((String)attributes.get("email"))
                .pictures((String)attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {    // User 엔티티로 변환해주는 메소드
                                // 이 클래스 입장에서 엔티티를 생성하는 시점 : 처음 가입할 때
        return User.builder()
                .name(name)
                .email(email)
                .picture(pictures)
                .role(Role.GUEST)   // 따라서 가입할 때 기본 권한을 GUEST로
                .build();
    }
}
