package com.example.redcollar1.services;

import com.example.redcollar1.exception.NotFoundEntityException;
import com.example.redcollar1.models.entities.Person;
import com.example.redcollar1.models.entities.VideoContent;
import com.example.redcollar1.repository.PersonRepository;
import com.example.redcollar1.repository.VideoContentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CheckDataService {
    public VideoContent verificationOfExistenceContentById(Long id, VideoContentRepository contentRepository) {
        Optional<VideoContent> optionalVideoContent = contentRepository.findById(id);
        if (optionalVideoContent.equals(Optional.empty())) {
            throw new NotFoundEntityException(id);
        }

        return optionalVideoContent.orElse(new VideoContent());
    }

    public Person verificationOfExistencePersonById(Long id, PersonRepository personRepository) {
        Optional<Person> person = personRepository.findById(id);

        if (person.equals(Optional.empty())) {
            throw new NotFoundEntityException(id);
        }

        return person.orElse(new Person());
    }
}
