package com.koorung.book.freelecspringbootaws.web;

import com.koorung.book.freelecspringbootaws.service.posts.PostsService;
import com.koorung.book.freelecspringbootaws.web.dto.PostsListResponseDto;
import com.koorung.book.freelecspringbootaws.web.dto.PostsResponseDto;
import com.koorung.book.freelecspringbootaws.web.dto.PostsSaveRequestDto;
import com.koorung.book.freelecspringbootaws.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@RequestBody PostsUpdateRequestDto requestDto, @PathVariable Long id) {
        return postsService.update(requestDto, id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        return postsService.delete(id);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @GetMapping("/api/v1/posts")
    public List<PostsListResponseDto> findAllDesc() {
        return postsService.findAllDesc();
    }
}
