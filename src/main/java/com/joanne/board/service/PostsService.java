package com.joanne.board.service;

import com.joanne.board.domain.posts.Posts;
import com.joanne.board.domain.posts.PostsRepository;
import com.joanne.board.web.dto.PostsListResponse;
import com.joanne.board.web.dto.PostsResponse;
import com.joanne.board.web.dto.PostsSaveRequest;
import com.joanne.board.web.dto.PostsUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    public Long save(PostsSaveRequest request) {
        final Posts posts = postsRepository.save(request.toEntity());
        return posts.getId();
    }

    @Transactional(readOnly = true)
    public List<PostsListResponse> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequest request) {
        final Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. ID=" + id));
        posts.update(request.getTitle(), request.getContent());
        return id;
    }

    public PostsResponse findById(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. ID=" + id));
        return new PostsResponse(posts);
    }

    @Transactional
    public void delete(Long id) {
        final Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. ID=" + id));
        postsRepository.delete(posts);
    }
}
