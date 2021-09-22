package com.example.redcollar1.services;

import com.example.redcollar1.exception.ValidateEmailException;
import com.example.redcollar1.models.entities.Person;
import com.example.redcollar1.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceJPA {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ValidatorService validatorService;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public void save( String name,  Long age,  String email,
                      String login,  String pass) throws ValidateEmailException {

        if(!validatorService.validateEmail(email)){
            throw new ValidateEmailException(email);
        }

        Person employee = personRepository.save(
                Person.builder()
                        .name(name)
                        .age(age)
                        .email(email)
                        .login(login)
                        .pass(pass)
                        .build()
        );
        personRepository.save(employee);
    }
}
