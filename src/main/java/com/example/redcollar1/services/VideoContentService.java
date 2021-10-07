package com.example.redcollar1.services;

import com.example.redcollar1.exception.IncorrectNameContentException;
import com.example.redcollar1.models.dto.response.VideoContentDtoResponse;
import com.example.redcollar1.models.entities.Person;
import com.example.redcollar1.models.entities.VideoContent;
import com.example.redcollar1.models.factories.VideoContentDtoFactory;
import com.example.redcollar1.repository.PersonRepository;
import com.example.redcollar1.repository.VideoContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class VideoContentService {


    private VideoContentDtoFactory contentDtoFactory;
    private VideoContentRepository contentRepository;
    private PersonRepository personRepository;
    private CheckDataService checkDataService;

    @Autowired
    public VideoContentService(VideoContentDtoFactory contentDtoFactory, VideoContentRepository contentRepository, PersonRepository personRepository, CheckDataService checkDataService) {
        this.contentDtoFactory = contentDtoFactory;
        this.contentRepository = contentRepository;
        this.personRepository = personRepository;
        this.checkDataService = checkDataService;
    }


    public VideoContentDtoResponse update(Long id, String name, String genres,
                                          String image, String description) {

        VideoContent entity = checkDataService.verificationOfExistenceContentById(id, contentRepository);
        entity.setName(name);
        entity.setGenres(genres);
        entity.setDescription(description);
        entity.setImage(image);
        return contentDtoFactory.makeEmployeeDto(contentRepository.save(entity));
    }

    public void delete(Long id) {
        contentRepository.deleteById(id);
    }

    public List<VideoContentDtoResponse> findAll() {

        return Stream.of(contentRepository.findAll())
                .map(contents -> contentDtoFactory.makeEmployeeDto((VideoContent) contents)).collect(Collectors.toList());
    }

    public VideoContentDtoResponse save(String name, String genres,
                                        String image, String description, Long idPerson) throws IncorrectNameContentException {

        Validation.validateNameContent(name);

        Person person = checkDataService.verificationOfExistencePersonById(idPerson, personRepository);
        VideoContent employee = contentRepository.save(
                VideoContent.builder()
                        .name(name)
                        .genres(genres)
                        .image(image)
                        .description(description)
                        .IdPerson(person.getId())
                        .build()
        );

        return contentDtoFactory.makeEmployeeDto(employee);
    }


}
