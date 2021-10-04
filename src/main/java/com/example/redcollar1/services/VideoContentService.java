package com.example.redcollar1.services;

import com.example.redcollar1.exception.IncorrectNameContentException;
import com.example.redcollar1.models.dto.VideoContentDto;
import com.example.redcollar1.models.entities.Person;
import com.example.redcollar1.models.entities.VideoContent;
import com.example.redcollar1.models.factories.VideoContentDtoFactory;
import com.example.redcollar1.repository.PersonRepository;
import com.example.redcollar1.repository.VideoContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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


    public VideoContentDto update(Long id, String name, String genres,
                                  String image, String description) {

        checkDataService.verificationOfExistenceContentById(id, contentRepository);
        VideoContent entity = contentRepository.getById(id);
        entity.setName(name);
        entity.setGenres(genres);
        entity.setDescription(description);
        entity.setImage(image);
        return contentDtoFactory.makeEmployeeDto(contentRepository.save(entity));
    }

    public void delete(Long id) {
        contentRepository.deleteById(id);
    }

    public List<VideoContentDto> findAll() {
        return contentRepository.findAll().stream()
                .map(s -> contentDtoFactory.makeEmployeeDto(s)).collect(Collectors.toList());
    }

    public VideoContentDto save(String name, String genres,
                                String image, String description, Long idPerson) throws IncorrectNameContentException {

        Validation.validateNameContent(name);

        checkDataService.verificationOfExistenceContentById(idPerson, contentRepository);
        Person byId = personRepository.getById(idPerson);
        VideoContent employee = contentRepository.save(
                VideoContent.builder()
                        .name(name)
                        .genres(genres)
                        .image(image)
                        .description(description)
                        .person(byId)
                        .build()
        );

        return contentDtoFactory.makeEmployeeDto(employee);
    }


}
