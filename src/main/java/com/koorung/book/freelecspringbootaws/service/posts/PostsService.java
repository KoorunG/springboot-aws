package com.koorung.book.freelecspringbootaws.service.posts;

import com.koorung.book.freelecspringbootaws.domain.posts.Posts;
import com.koorung.book.freelecspringbootaws.domain.posts.PostsRepository;
import com.koorung.book.freelecspringbootaws.web.dto.PostsResponseDto;
import com.koorung.book.freelecspringbootaws.web.dto.PostsSaveRequestDto;
import com.koorung.book.freelecspringbootaws.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(PostsUpdateRequestDto requestDto, Long id) {

        Posts entity = getEntity(id);

        // JPA의 변경감지를 이용한 업데이트
        // 비즈니스로직은 엔티티 안에 존재함
        entity.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = getEntity(id);
        return new PostsResponseDto(entity);
    }

    // 저장소에서 키값으로 엔티티 조회
    private Posts getEntity(Long id) {
        return postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
    }
}
