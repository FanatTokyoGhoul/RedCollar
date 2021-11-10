package com.example.redcollar1.services;

import com.example.redcollar1.models.entities.Person;
import com.example.redcollar1.models.entities.VideoContent;
import com.example.redcollar1.models.factories.PersonDtoFactory;
import com.example.redcollar1.repository.PersonRepository;
import common.lib.exception.IncorrectEmailException;
import common.lib.exception.NotFoundEntityException;
import common.lib.models.dto.request.PersonDtoRequest;
import common.lib.models.dto.response.PersonDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonDtoFactory personDtoFactory;

    @Autowired
    public PersonService(PersonRepository personRepository, PersonDtoFactory personDtoFactory) {
        this.personRepository = personRepository;
        this.personDtoFactory = personDtoFactory;
    }

    public List<PersonDtoResponse> findAll() {
        List<PersonDtoResponse> personDtos = new ArrayList<>();
        personRepository.findAll().forEach(person -> {
            personDtos.add(personDtoFactory.toPersonDtoResponse(person));
        });
        return personDtos;
    }

    public List<PersonDtoResponse> findPersonWithMoreContentThanANumber(int number) {
        List<PersonDtoResponse> personDtos = new ArrayList<>();

        personRepository.findAll().forEach(person -> {
            if (person.getContents().size() > number) {
                personDtos.add(personDtoFactory.toPersonDtoResponse(person));
            }
        });

        return personDtos;
    }

    public List<PersonDtoResponse> findPersonMakingContent(String genres) {
        List<PersonDtoResponse> personDtos = new ArrayList<>();

        personRepository.findAll().forEach(person -> {
            for (VideoContent videoContent : person.getContents()) {
                if (videoContent.getGenres().equals(genres)) {
                    personDtos.add(personDtoFactory.toPersonDtoResponse(person));
                    break;
                }
            }
        });

        return personDtos;
    }

    public PersonDtoResponse create(PersonDtoRequest personDto) {
        Person employee = personDtoFactory.makePerson(personDto);

        return personDtoFactory.toPersonDtoResponse(personRepository.save(employee));
    }

    public PersonDtoResponse getPerson(Long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        Person person = optionalPerson.orElseThrow(() -> new NotFoundEntityException(id));

        return personDtoFactory.toPersonDtoResponse(person);
    }

    public PersonDtoResponse update(Long id, PersonDtoRequest personDtoRequest) {

        Optional<Person> optionalPerson = personRepository.findById(id);
        Person person = optionalPerson.orElseThrow(() -> new NotFoundEntityException(id));

        person.setName(personDtoRequest.getName());
        person.setAge(personDtoRequest.getAge());
        person.setEmail(personDtoRequest.getEmail());
        person.setDateOfBirth(personDtoRequest.getDateOfBirth());

        return personDtoFactory.toPersonDtoResponse(personRepository.save(person));
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }
}
