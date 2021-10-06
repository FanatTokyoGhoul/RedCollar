package com.example.redcollar1.repository;

import com.example.redcollar1.models.entities.VideoContent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoContentRepository extends CrudRepository<VideoContent, Long> {

}
