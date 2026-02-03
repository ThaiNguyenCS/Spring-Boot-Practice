package com.example.learning.social_media.repository.jpa;

import com.example.learning.social_media.dto.MediaDTO;
import com.example.learning.social_media.dto.MediaDTO2;
import com.example.learning.social_media.entity.Medias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Medias, Long> {
    @Query("""
            SELECT new com.example.learning.social_media.dto.MediaDTO(m.url, m.type, m.postId)
            FROM Medias m
            WHERE m.postId IN :postIds
            """)
    List<MediaDTO> findAllByPostIdIn(List<Long> postIds);

    @Query("""
            SELECT m.url as url, m.type as type, m.postId as postId
            FROM Medias m
            WHERE m.postId IN :postIds
            """)
    List<MediaDTO2> findAllByPostIdIn2(List<Long> postIds);
}
