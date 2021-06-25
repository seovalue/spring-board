package com.joanne.board.web;

import com.joanne.board.service.PostsService;
import com.joanne.board.web.dto.PostsListResponse;
import com.joanne.board.web.dto.PostsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        final List<PostsListResponse> postsListResponses = postsService.findAllDesc();
        model.addAttribute("posts", postsListResponses);
        return "index";
    }

    @GetMapping("/posts/{id}")
    public String update(@PathVariable Long id, Model model) {
        final PostsResponse response = postsService.findById(id);
        model.addAttribute("post", response);
        return "posts-update";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
