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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PersonService {

    private PersonRepository personRepository;
    private PersonDtoFactory personDtoFactory;
    private CheckDataService checkDataService;

    @Autowired
    public PersonService(PersonRepository personRepository, PersonDtoFactory personDtoFactory, CheckDataService checkDataService) {
        this.personRepository = personRepository;
        this.personDtoFactory = personDtoFactory;
        this.checkDataService = checkDataService;
    }

    public List<PersonDto> findAll() {
        List<PersonDto> personDtos = new ArrayList<>();
        for(Person person : personRepository.findAll()){
            personDtos.add(personDtoFactory.makeEmployeeDto(person));
        }
        return personDtos;
    }

    public List<PersonDto> findPersonWithMoreContentThanANumber(int number) {
        return new ArrayList<>();
    }

    public PersonDto save(PersonDto personDto) throws IncorrectEmailException {

        Validation.validateEmail(personDto.getEmail());

        Person employee = personRepository.save(
                Person.builder()
                        .name(personDto.getName())
                        .age(personDto.getAge())
                        .dateOfBirth(personDto.getDateOfBirth())
                        .email(personDto.getEmail())
                        .login(personDto.getLogin())
                        .pass(personDto.getPass())
                        .build()
        );

        return personDtoFactory.makeEmployeeDto(personRepository.save(employee));
    }

    public PersonDto update(Long id, String name, Long age, String email,
                            String login, String pass, LocalDate dateOfBirth) throws IncorrectEmailException {

        Person person = checkDataService.verificationOfExistencePersonById(id, personRepository);


        person.setName(name);
        person.setAge(age);
        person.setEmail(email);
        person.setLogin(login);
        person.setPass(pass);
        person.setDateOfBirth(dateOfBirth);

        personRepository.save(person);

        return personDtoFactory.makeEmployeeDto(person);
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }
}
