package com.example.learning.social_media.controller;

import com.example.learning.social_media.dto.KeySetPage;
import com.example.learning.social_media.service.PostService;
import com.example.learning.social_media.dto.PostDetail;
import com.example.learning.social_media.entity.Posts;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @PostMapping("")
    public ResponseEntity<Posts> createPost(@RequestBody Posts post) {
        return ResponseEntity.ok(postService.createPost(post));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Posts> getPostsById(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.findByPostId(postId));
    }

    @GetMapping("/by-users/{userId}")
    public ResponseEntity<List<Posts>> getPostsByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(postService.findByUserId(userId));
    }

    @GetMapping("/by-users/{userId}/lazy-load")
    public ResponseEntity<List<Posts>> getPostsByUserIdLazyLoad(@PathVariable Integer userId) {
        return ResponseEntity.ok(postService.findByUserIdLazyLoad(userId));
    }

    @GetMapping("/by-users/{userId}/detail")
    public ResponseEntity<List<PostDetail>> getPostDetailsByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(postService.findPostDetailsByUserId(userId));
    }

    @GetMapping("/by-users/{userId}/detail2")
    public ResponseEntity<List<PostDetail>> getPostDetailsByUserIdUsingInterfaceProjection(@PathVariable Integer userId) {
        return ResponseEntity.ok(postService.findPostDetailsByUserIdUsingInterfaceProjection(userId));
    }

    @GetMapping("/all/naive-paging")
    public ResponseEntity<Page<Posts>> getAllPostsNaiveOffset(@PageableDefault(
            size = 20,
            page = 0,
            sort = {"id"},
            direction = Sort.Direction.ASC
    ) Pageable pageable) {
        return ResponseEntity.ok(postService.findAllPosts(pageable));
    }

    @GetMapping("/all/advanced-paging")
    public ResponseEntity<KeySetPage<Posts, Long>> getAllPostsAdvancedPaging(@PageableDefault(
            size = 20,
            page = 0,
            sort = {"id"},
            direction = Sort.Direction.ASC) Pageable pageable, @RequestParam (required = false) Long lastId) {
        return ResponseEntity.ok(postService.findAllPostsAdvancePaging(lastId, pageable));
    }
}
