package com.example.learning.social_media.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MediaDTO {
    private String type;
    private String url;
    private Long postId;
}
