package com.example.learning.social_media.repository.jpa;

import com.example.learning.social_media.dto.PostDetail;
import com.example.learning.social_media.entity.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {
    List<Posts> findAllByUserId(Integer userId);
    Page<Posts> findAll(Pageable pageable);

    @Query("""
        SELECT p FROM Posts p
        WHERE (:lastId IS NULL OR p.id > :lastId)
        ORDER BY p.id ASC
    """)
    List<Posts> findNext(
            @Param("lastId") Long lastId,
            Pageable pageable // getPageSize() for limiting the number of results
    );
}
