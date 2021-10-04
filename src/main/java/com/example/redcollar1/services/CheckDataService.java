package com.example.redcollar1.services;

import com.example.redcollar1.exception.NotFoundEntityException;
import com.example.redcollar1.repository.PersonRepository;
import com.example.redcollar1.repository.VideoContentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

//TODO Спросить у Макса нормально ли так делать

@Service
public class CheckDataService {
    public void verificationOfExistenceContentById(Long id, VideoContentRepository contentRepository) {
        if (contentRepository.findById(id).equals(Optional.empty())) {
            throw new NotFoundEntityException(id);
        }
    }

    public void verificationOfExistencePersonById(Long id, PersonRepository personRepository) {
        if (personRepository.findById(id).equals(Optional.empty())) {
            throw new NotFoundEntityException(id);
        }
    }
}
