package com.example.learning.social_media.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "partition_medias", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartitionMedias {
    @Id
    Long id;
    Long postId;
    String type;
    String url;
}
