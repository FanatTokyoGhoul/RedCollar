package com.example.redcollar1.models.factories;

import com.example.redcollar1.models.dto.request.VideoContentDtoRequest;
import com.example.redcollar1.models.dto.response.VideoContentDtoResponse;
import com.example.redcollar1.models.entities.VideoContent;

public class VideoContentDtoFactory {
    public VideoContentDtoResponse makeEmployeeDto(VideoContent entity) {

        return VideoContentDtoResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .genres(entity.getGenres())
                .description(entity.getDescription())
                .image(entity.getImage())
                .idPerson(entity.getIdPerson())
                .build();
    }

    public VideoContent makeEntity(VideoContentDtoRequest videoContent) {
        return VideoContent.builder()
                .name(videoContent.getName())
                .genres(videoContent.getGenres())
                .description(videoContent.getDescription())
                .image(videoContent.getImage())
                .idPerson(videoContent.getIdPerson())
                .build();
    }
}