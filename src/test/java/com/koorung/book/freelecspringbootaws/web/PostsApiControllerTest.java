package com.koorung.book.freelecspringbootaws.web;

import com.koorung.book.freelecspringbootaws.domain.posts.Posts;
import com.koorung.book.freelecspringbootaws.domain.posts.PostsRepository;
import com.koorung.book.freelecspringbootaws.web.dto.PostsSaveRequestDto;
import com.koorung.book.freelecspringbootaws.web.dto.PostsUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 테스트 환경에서 랜덤 포트를 사용하겠다는 명시
class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    private ResponseEntity<Long> responseEntity;

    @Autowired
    private TestRestTemplate restTemplate;
    // TestRestTemplate : 스프링부트에서 테스트 편의를 위해 제공하는 클래스
    // MockTesting 과의 차이는 실제 서블릿 컨테이너 실행 여부이며 RestTestTemplate은 컨테이너를 직접 실행한다.

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void cleanup() {
        postsRepository.deleteAll();
    }

    // 등록
    @Test
    void post() {

        // given & when
        setup();

        // then
        assertThat(responseEntity).extracting("statusCode").isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
    }

    // 조회
    @Test
    void findAll() {

        // given & when
        setup();

        // + when
        List<Posts> all = postsRepository.findAll();

        // then
        assertThat(all).extracting("title").containsExactly("title");
        assertThat(all).extracting("content").containsExactly("content");

    }

    // 수정
    @Test
    void update() {

        Posts saved = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updateId = saved.getId();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        PostsUpdateRequestDto updateRequestDto = PostsUpdateRequestDto.builder()
                .title("타이틀수정1")
                .content("콘텐츠수정1")
                .build();

        HttpEntity<PostsUpdateRequestDto> requestDtoHttpEntity = new HttpEntity<>(updateRequestDto);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestDtoHttpEntity, Long.class);

        List<Posts> all = postsRepository.findAll();

        // 업데이트 행위 여부 검증
        assertThat(responseEntity).extracting("statusCode").isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        // 업데이트 된 값 검증
        assertThat(all).extracting("title").containsExactly("타이틀수정1");
        assertThat(all).extracting("content").containsExactly("콘텐츠수정1");
    }


    private void setup(){
        String title = "title";
        String content = "content";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
    }
}