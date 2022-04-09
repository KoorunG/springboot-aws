package com.koorung.book.freelecspringbootaws.service.posts;

import com.koorung.book.freelecspringbootaws.domain.posts.Posts;
import com.koorung.book.freelecspringbootaws.domain.posts.PostsRepository;
import com.koorung.book.freelecspringbootaws.web.dto.PostsListResponseDto;
import com.koorung.book.freelecspringbootaws.web.dto.PostsResponseDto;
import com.koorung.book.freelecspringbootaws.web.dto.PostsSaveRequestDto;
import com.koorung.book.freelecspringbootaws.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
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

    @Transactional
    public Long delete(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        postsRepository.delete(entity);
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = getEntity(id);
        return new PostsResponseDto(entity);
    }

    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()// 1. List<Posts>를 얻어온 뒤
                .stream()                   // 2. stream을 돌려
                .map(PostsListResponseDto::new) // 3. 각각의 리턴값을 DTO로 변환하고
                .collect(toList()); // 4. collect(toList())로 리스트로 변환한 값을 리턴함
    }

    // 저장소에서 키값으로 엔티티 조회
    private Posts getEntity(Long id) {
        return postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
    }

    @PostConstruct  // 견본 데이터 삽입
    private void init() {
        postsRepository.save(Posts.builder()
                .title("테스트제목")
                .author("테스트작성자")
                .content("테스트내용")
                .build());
    }
}
