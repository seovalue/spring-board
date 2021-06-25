package com.joanne.board.web;

import com.joanne.board.domain.posts.Posts;
import com.joanne.board.domain.posts.PostsRepository;
import com.joanne.board.web.dto.PostsSaveRequest;
import com.joanne.board.web.dto.PostsUpdateRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    void tearDown() {
        postsRepository.deleteAll();
    }

    @Test
    @DisplayName("포스트를 등록한다.")
    void savePosts() {
        String title = "title";
        String content = "content";

        final PostsSaveRequest request = PostsSaveRequest.builder()
                .title(title)
                .content(content)
                .author("joanne")
                .build();
        String url = "http://localhost:" + port + "/api/v1/posts";

        final ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, request, Long.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        final List<Posts> posts = postsRepository.findAll();
        assertThat(posts.get(0).getTitle()).isEqualTo(title);
        assertThat(posts.get(0).getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("포스트를 수정한다.")
    void update() {
        final Posts savedPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "expected Title";
        String expectedContent = "expected Content";

        final PostsUpdateRequest request = PostsUpdateRequest.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;
        System.out.println(url);
        final HttpEntity<PostsUpdateRequest> requestHttpEntity = new HttpEntity<>(request);
        final ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestHttpEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        final List<Posts> posts = postsRepository.findAll();
        assertThat(posts.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(posts.get(0).getContent()).isEqualTo(expectedContent);
    }
}