package com.example.redcollar1.services;

import com.example.redcollar1.exception.IncorrectNameContentException;
import com.example.redcollar1.exception.NotFoundEntityException;
import com.example.redcollar1.models.dto.request.VideoContentDtoRequest;
import com.example.redcollar1.models.dto.response.PersonDtoResponse;
import com.example.redcollar1.models.dto.response.VideoContentDtoResponse;
import com.example.redcollar1.models.entities.Person;
import com.example.redcollar1.models.entities.VideoContent;
import com.example.redcollar1.models.factories.VideoContentDtoFactory;
import com.example.redcollar1.repository.PersonRepository;
import com.example.redcollar1.repository.VideoContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class VideoContentService {


    private VideoContentDtoFactory contentDtoFactory;
    private VideoContentRepository contentRepository;
    private PersonRepository personRepository;

    @Autowired
    public VideoContentService(VideoContentDtoFactory contentDtoFactory, VideoContentRepository contentRepository, PersonRepository personRepository) {
        this.contentDtoFactory = contentDtoFactory;
        this.contentRepository = contentRepository;
        this.personRepository = personRepository;
    }


    public VideoContentDtoResponse update(Long id, VideoContentDtoRequest videoContentDtoRequest) {

        VideoContent entity = contentRepository.findById(id).orElseThrow(() -> new NotFoundEntityException(id));
        entity.setName(videoContentDtoRequest.getName());
        entity.setGenres(videoContentDtoRequest.getGenres());
        entity.setDescription(videoContentDtoRequest.getDescription());
        entity.setImage(videoContentDtoRequest.getImage());
        return contentDtoFactory.makeEmployeeDto(contentRepository.save(entity));
    }

    public void delete(Long id) {
        contentRepository.deleteById(id);
    }

    public List<VideoContentDtoResponse> findAll() {
        List<VideoContentDtoResponse> personDtos = new ArrayList<>();
        for (VideoContent content : contentRepository.findAll()) {
            personDtos.add(contentDtoFactory.makeEmployeeDto(content));
        }
        return personDtos;
    }

    public VideoContentDtoResponse save(VideoContentDtoRequest videoContentDtoRequest) throws IncorrectNameContentException {

        Validation.validateNameContent(videoContentDtoRequest.getName());

        VideoContent employee = contentDtoFactory.makeEntity(videoContentDtoRequest);

        return contentDtoFactory.makeEmployeeDto(contentRepository.save(employee));
    }


}
