package com.koorung.book.freelecspringbootaws.web.dto;

import com.koorung.book.freelecspringbootaws.domain.posts.Posts;
import lombok.Getter;

// Post 응답 DTO
@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    // 생성자 단계에서 엔티티를 DTO로 변환
    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
