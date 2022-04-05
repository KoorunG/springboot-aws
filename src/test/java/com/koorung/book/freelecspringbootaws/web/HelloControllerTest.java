package com.koorung.book.freelecspringbootaws.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest
@WebMvcTest(controllers = HelloController.class) // Web에 테스트를 집중할 수 있는 애노테이션..
class HelloControllerTest {

    @Autowired
    private MockMvc mvc;    // mvc용 Mock 객체 (http를 테스트 하기 좋게 스프링에서 제공하는 클래스...)

    @Test
    @DisplayName("hello가 리턴됩니다.")
    void hello() throws Exception {
        String hello = "hello";
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    @DisplayName("responseDto가 리턴됩니다")
    void responseDto() throws Exception {

        String name = "koorung";
        int amount = 30;

        mvc.perform(get("/hello/dto?name="+name+"&amount="+amount))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))        // jsonPath : JSON 응답값을 필드별로 검증할 수 있는 메소드.
                .andExpect(jsonPath("$.amount", is(amount)));   // $를 기준으로 필드명을 명시함
    }
}