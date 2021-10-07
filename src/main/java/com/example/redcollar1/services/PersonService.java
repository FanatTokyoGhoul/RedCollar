package com.example.redcollar1.services;

import com.example.redcollar1.exception.IncorrectEmailException;
import com.example.redcollar1.exception.NotFoundEntityException;
import com.example.redcollar1.models.dto.request.PersonDtoRequest;
import com.example.redcollar1.models.dto.response.PersonDtoResponse;
import com.example.redcollar1.models.entities.Person;
import com.example.redcollar1.models.factories.PersonDtoFactory;
import com.example.redcollar1.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        for (Person person : personRepository.findAll()) {
            personDtos.add(personDtoFactory.makeEmployeeDto(person));
        }
        return personDtos;
    }

    public List<PersonDtoResponse> findPersonWithMoreContentThanANumber(int number) {
        List<PersonDtoResponse> personDtos = new ArrayList<>();
        for (Person person : personRepository.findAll()) {
            if (person.getContents().size() > number) {
                personDtos.add(personDtoFactory.makeEmployeeDto(person));

            }
        }

        return personDtos;
    }

    public PersonDtoResponse save(PersonDtoRequest personDto) throws IncorrectEmailException {

        Validation.validateEmail(personDto.getEmail());

        Person employee = personDtoFactory.makeEntity(personDto);

        return personDtoFactory.makeEmployeeDto(personRepository.save(employee));
    }

    public PersonDtoResponse update(Long id, PersonDtoRequest personDtoRequest) throws IncorrectEmailException {

        Optional<Person> optionalPerson = personRepository.findById(id);
        Person person = optionalPerson.orElseThrow(() -> new NotFoundEntityException(id));

        person.setName(personDtoRequest.getName());
        person.setAge(personDtoRequest.getAge());
        person.setEmail(personDtoRequest.getEmail());
        person.setLogin(personDtoRequest.getLogin());
        person.setPass(personDtoRequest.getPass());
        person.setDateOfBirth(personDtoRequest.getDateOfBirth());

        personRepository.save(person);

        return personDtoFactory.makeEmployeeDto(person);
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }
}
