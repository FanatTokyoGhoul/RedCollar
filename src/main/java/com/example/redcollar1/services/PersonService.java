package com.example.redcollar1.services;

import com.example.redcollar1.exception.IncorrectEmailException;
import com.example.redcollar1.models.dto.PersonDto;
import com.example.redcollar1.models.entities.Person;
import com.example.redcollar1.models.factories.PersonDtoFactory;
import com.example.redcollar1.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;
    private PersonDtoFactory personDtoFactory;

    @Autowired
    public PersonService(PersonRepository personRepository, PersonDtoFactory personDtoFactory) {
        this.personRepository = personRepository;
        this.personDtoFactory = personDtoFactory;
    }

    public List<PersonDto> findAll() {
        return personRepository.findAll().stream()
                .map(person -> personDtoFactory.makeEmployeeDto(person)).collect(Collectors.toList());
    }

    public List<PersonDto> findPersonWithMoreContentThanANumber(int number){
        return personRepository.findAll().stream().filter(person -> person.getContents().size() > number)
                .map(person -> personDtoFactory.makeEmployeeDto(person)).collect(Collectors.toList());
    }

    public PersonDto save(String name, Long age, String email,
                          String login, String pass, LocalDate dateOfBirth) throws IncorrectEmailException {

        Validation.validateEmail(email);

        Person employee = personRepository.save(
                Person.builder()
                        .name(name)
                        .age(age)
                        .dateOfBirth(dateOfBirth)
                        .email(email)
                        .login(login)
                        .pass(pass)
                        .build()
        );
        personRepository.save(employee);
        return personDtoFactory.makeEmployeeDto(employee);
    }

    public void delete(Long id){
        personRepository.deleteById(id);
    }
}
