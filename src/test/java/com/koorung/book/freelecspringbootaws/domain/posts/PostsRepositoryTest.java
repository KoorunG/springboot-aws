package com.koorung.book.freelecspringbootaws.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository repository;

    @AfterEach  // 테스트가 모두 수행되고 난 뒤 클린업
    void cleanup() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("게시글 저장 & 불러오기")
    void saveAndRetrieve() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        repository.save(Posts.builder().title(title).content(content).author("koorung@naver.com").build());

        // when
        List<Posts> lists = repository.findAll();

        // then
        assertThat(lists).extracting("title", String.class).containsExactly("테스트 게시글");
        assertThat(lists).extracting("content", String.class).containsExactly("테스트 본문");
        assertThat(lists).extracting("author", String.class).containsExactly("koorung@naver.com");
    }

    @Test
    @DisplayName("BaseTimeEntity 등록 - JPA Auditing")
    void baseTimeEntityTest() {
        Posts saved = repository.save(Posts.builder().title("title").content("content").author("author").build());

        System.out.println("created : " + saved.getCreatedDate());
        System.out.println("modified : " + saved.getModifiedDate());
        assertThat(saved.getCreatedDate()).isBefore(LocalDateTime.now()).isAfter(LocalDateTime.now().minusSeconds(1));
        assertThat(saved.getModifiedDate()).isBefore(LocalDateTime.now()).isAfter(LocalDateTime.now().minusSeconds(1));
    }
}