package com.example.learning.social_media.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Medias {
    @Id
    Long id;
    Long postId;
    String type;
    String url;
}
