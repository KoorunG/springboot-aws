package com.koorung.book.freelecspringbootaws.web;

import com.koorung.book.freelecspringbootaws.config.auth.dto.SessionUser;
import com.koorung.book.freelecspringbootaws.service.posts.PostsService;
import com.koorung.book.freelecspringbootaws.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;    // 서비스 DI
    private final HttpSession httpSession;      // 세션 DI

    @GetMapping("/")
    public String index(Model model){           // 페이지가 로딩될 때 postsService.findAllDesc()로 게시글 목록을 바로 불러옴
                                                // api로 호출하고 싶으면 ApiController에서 추가할 필요가 있음..
        /* 글목록 불러오기 기능 추가 */
        model.addAttribute("posts", postsService.findAllDesc());

        /* 유저 로그인 기능 추가 */
        SessionUser user = (SessionUser)httpSession.getAttribute("user");

        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String saveForm() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("posts", dto);
        return "posts-update";
    }
}
