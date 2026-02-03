package com.example.learning.social_media.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
public class PostDetail {
    Long id;
    String content;
    LocalDateTime createdAt;
    List<MediaDTO> medias;
    List<MediaDTO2> medias2;

    public static PostDetail withMedias(
            Long id,
            String content,
            LocalDateTime createdAt,
            List<MediaDTO> medias
    ) {
        return new PostDetail(id, content, createdAt, medias, List.of());
    }

    public static PostDetail withMedias2(
            Long id,
            String content,
            LocalDateTime createdAt,
            List<MediaDTO2> medias2
    ) {
        return new PostDetail(id, content, createdAt, List.of(), medias2);
    }
}
