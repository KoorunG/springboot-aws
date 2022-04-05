package com.koorung.book.freelecspringbootaws.service.posts;

import com.koorung.book.freelecspringbootaws.domain.posts.Posts;
import com.koorung.book.freelecspringbootaws.domain.posts.PostsRepository;
import com.koorung.book.freelecspringbootaws.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}
