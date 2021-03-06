package com.example.redcollar1.models.factories;

import com.example.redcollar1.models.entities.VideoContent;
import common.lib.models.dto.request.VideoContentDtoRequest;
import common.lib.models.dto.response.VideoContentDtoResponse;
import org.springframework.stereotype.Component;

@Component
public class VideoContentDtoFactory {
    public VideoContentDtoResponse toVideoContentDtoResponse(VideoContent entity) {

        return VideoContentDtoResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .genres(entity.getGenres())
                .description(entity.getDescription())
                .image(entity.getImage())
                .idPerson(entity.getIdPerson())
                .build();
    }

    public VideoContent toVideoContent(VideoContentDtoRequest videoContent) {
        return VideoContent.builder()
                .name(videoContent.getName())
                .genres(videoContent.getGenres())
                .description(videoContent.getDescription())
                .image(videoContent.getImage())
                .idPerson(videoContent.getIdPerson())
                .build();
    }
}