package com.koorung.book.freelecspringbootaws.web.dto;


import com.koorung.book.freelecspringbootaws.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
Update용 요청 DTO
키값인 id와 author은 업데이트 하지 않음
빌더 패턴을 적용하여 DTO를 쉽게 만들 수 있다.
 */
@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {

    private String title;
    private String content;

    @Builder
    public PostsUpdateRequestDto(String title, String content){
        this.title = title;
        this.content = content;
    }
}
