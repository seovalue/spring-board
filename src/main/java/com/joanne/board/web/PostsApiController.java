package com.joanne.board.web;

import com.joanne.board.service.PostsService;
import com.joanne.board.web.dto.PostsResponse;
import com.joanne.board.web.dto.PostsSaveRequest;
import com.joanne.board.web.dto.PostsUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/posts")
    public Long save(@RequestBody PostsSaveRequest postsSaveRequest) {
        return postsService.save(postsSaveRequest);
    }

    @PutMapping("/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequest postsUpdateRequest) {
        return postsService.update(id, postsUpdateRequest);
    }

    @GetMapping("/posts/{id}")
    public PostsResponse findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
