package com.example.learning.social_media.service;

import com.example.learning.social_media.dto.KeySetPage;
import com.example.learning.social_media.dto.MediaDTO;
import com.example.learning.social_media.dto.MediaDTO2;
import com.example.learning.social_media.dto.PostDetail;
import com.example.learning.social_media.entity.PartitionMedias;
import com.example.learning.social_media.entity.Posts;
import com.example.learning.social_media.repository.jpa.MediaRepository;
import com.example.learning.social_media.repository.jpa.PartitionMediaRepository;
import com.example.learning.social_media.repository.jpa.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostsRepository postsRepository;
    private final MediaRepository mediaRepository;
    private final PartitionMediaRepository partitionMediaRepository;

    public List<Posts> findByUserId(Integer userId) {
        return postsRepository.findAllByUserId(userId);
    }

    public List<Posts> findByUserIdLazyLoad(Integer userId) {
        List<Posts> posts = postsRepository.findAllByUserId(userId);
//        posts.forEach(post -> post.getMediasList().size());
        return posts;
    }

    public List<PostDetail> findPostDetailsByUserId(Integer userId) {
        List<Posts> posts = postsRepository.findAllByUserId(userId);
        List<MediaDTO> medias = mediaRepository.findAllByPostIdIn(posts.stream().map(Posts::getId).toList());
        Map<Long, List<MediaDTO>> mediaMap = medias.stream().collect(Collectors.groupingBy(MediaDTO::getPostId));
        return posts.stream().map(post -> PostDetail.withMedias(
                post.getId(),
                post.getContent(),
                post.getCreatedAt(),
                mediaMap.getOrDefault(post.getId(), List.of())
        )).toList();
    }

    public List<PostDetail> findPostDetailsByUserIdUsingInterfaceProjection(Integer userId) {
        List<Posts> posts = postsRepository.findAllByUserId(userId);
        List<MediaDTO2> medias = mediaRepository.findAllByPostIdIn2(posts.stream().map(Posts::getId).toList());
        Map<Long, List<MediaDTO2>> mediaMap = medias.stream().collect(Collectors.groupingBy(MediaDTO2::getPostId));
        return posts.stream().map(post -> PostDetail.withMedias2(
                post.getId(),
                post.getContent(),
                post.getCreatedAt(),
                mediaMap.getOrDefault(post.getId(), List.of())
        )).toList();
    }

    public List<PartitionMedias> findPartitionMediaByPostId(Long postId) {
        return partitionMediaRepository.findAllByPostIdIn(List.of(postId));
    }

    public List<MediaDTO> findMediaByPostId(Long postId) {
        return mediaRepository.findAllByPostIdIn(List.of(postId));
    }

    public Page<Posts> findAllPosts(Pageable pageable) {
        return postsRepository.findAll(pageable);
    }

    public KeySetPage<Posts, Long> findAllPostsAdvancePaging(Long lastId, Pageable pageable) {
        Pageable fetchMore = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize() + 1,
                pageable.getSort()
        );
        List<Posts> posts = postsRepository.findNext(lastId, fetchMore);
        return KeySetPage.of(posts, pageable.getPageSize(), Posts::getId);
    }
}
