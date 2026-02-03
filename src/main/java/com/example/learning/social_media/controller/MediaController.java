package com.example.learning.social_media.controller;

import com.example.learning.social_media.dto.MediaDTO;
import com.example.learning.social_media.entity.PartitionMedias;
import com.example.learning.social_media.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/medias")
public class MediaController {
    private final PostService postService;
    @GetMapping("/{postId}")
    public ResponseEntity<List<MediaDTO>> getMediasByPostId(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.findMediaByPostId(postId));
    }

    @GetMapping("/{postId}/by-partition")
    public ResponseEntity<List<PartitionMedias>> getMediasByPostIdByPartition(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.findPartitionMediaByPostId(postId));
    }
}
