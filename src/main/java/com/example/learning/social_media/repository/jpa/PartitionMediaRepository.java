package com.example.learning.social_media.repository.jpa;

import com.example.learning.social_media.entity.PartitionMedias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartitionMediaRepository extends JpaRepository<PartitionMedias, Long> {
    List<PartitionMedias> findAllByPostIdIn(List<Long> postIds);
}
