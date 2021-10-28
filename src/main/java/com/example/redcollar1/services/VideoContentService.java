package com.example.redcollar1.services;


import com.example.redcollar1.models.entities.VideoContent;
import com.example.redcollar1.models.factories.VideoContentDtoFactory;
import com.example.redcollar1.repository.PersonRepository;
import com.example.redcollar1.repository.VideoContentRepository;
import common.lib.exception.IncorrectNameContentException;
import common.lib.exception.NotFoundEntityException;
import common.lib.models.dto.request.VideoContentDtoRequest;
import common.lib.models.dto.response.VideoContentDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoContentService {


    private final VideoContentDtoFactory contentDtoFactory;
    private final VideoContentRepository contentRepository;

    @Autowired
    public VideoContentService(VideoContentDtoFactory contentDtoFactory, VideoContentRepository contentRepository) {
        this.contentDtoFactory = contentDtoFactory;
        this.contentRepository = contentRepository;
    }


    public VideoContentDtoResponse getVideoContent(Long id) {
        VideoContent entity = contentRepository.findById(id).orElseThrow(() -> new NotFoundEntityException(id));

        return contentDtoFactory.toVideoContentDtoResponse(entity);
    }

    public VideoContentDtoResponse update(Long id, VideoContentDtoRequest videoContentDtoRequest) {

        VideoContent entity = contentRepository.findById(id).orElseThrow(() -> new NotFoundEntityException(id));
        entity.setName(videoContentDtoRequest.getName());
        entity.setGenres(videoContentDtoRequest.getGenres());
        entity.setDescription(videoContentDtoRequest.getDescription());
        entity.setImage(videoContentDtoRequest.getImage());
        return contentDtoFactory.toVideoContentDtoResponse(contentRepository.save(entity));
    }

    public void delete(Long id) {
        contentRepository.deleteById(id);
    }

    public List<VideoContentDtoResponse> findAll() {
        List<VideoContentDtoResponse> personDtos = new ArrayList<>();

        contentRepository.findAll().forEach(videoContent -> {
            personDtos.add(contentDtoFactory.toVideoContentDtoResponse(videoContent));
        });
        return personDtos;
    }

    public VideoContentDtoResponse create(VideoContentDtoRequest videoContentDtoRequest) {

        VideoContent employee = contentDtoFactory.toVideoContent(videoContentDtoRequest);

        return contentDtoFactory.toVideoContentDtoResponse(contentRepository.save(employee));
    }
}
