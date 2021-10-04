package com.example.redcollar1.repository;

import com.example.redcollar1.models.entities.VideoContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoContentRepository extends JpaRepository<VideoContent, Long> {

}
