package com.koorung.book.freelecspringbootaws.domain.posts;

import com.koorung.book.freelecspringbootaws.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class Posts extends BaseTimeEntity {

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

    // Posts 관련 비즈니스 로직 - 엔티티에 넣음
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
