package com.koorung.book.freelecspringbootaws.domain.posts;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class Posts {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder    // 해당 클래스의 빌더 패턴 클래스를 생성해주는 애노테이션.. 생성자에 포함된 필드만 빌더에 포함된다!
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
