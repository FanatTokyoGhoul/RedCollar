package com.example.redcollar1.models.factories;

import com.example.redcollar1.models.dto.VideoContentDto;
import com.example.redcollar1.models.entities.VideoContent;

public class VideoContentDtoFactory {
    public VideoContentDto makeEmployeeDto(VideoContent entity) {

        return VideoContentDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .genres(entity.getGenres())
                .description(entity.getDescription())
                .image(entity.getImage())
                .idUser(entity.getPerson().getId())
                .build();
    }
}