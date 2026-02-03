package com.example.learning.social_media.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "posts", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Posts {
    @Id
    Long id;
    Integer userId;
    String content;
    LocalDateTime createdAt;
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "postId")
//    List<Medias> mediasList;
}
