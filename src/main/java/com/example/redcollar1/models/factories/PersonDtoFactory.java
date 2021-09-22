package com.example.redcollar1.models.factories;

import com.example.redcollar1.models.dto.PersonDTO;
import com.example.redcollar1.models.entities.Person;

public class PersonDtoFactory {
    public PersonDTO makeEmployeeDto(Person entity){

        return PersonDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .age(entity.getAge())
                .email(entity.getEmail())
                .login(entity.getLogin())
                .pass(entity.getPass())
                .build();
    }
}
