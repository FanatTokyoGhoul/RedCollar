package com.example.redcollar1.models.factories;

import com.example.redcollar1.models.entities.Person;
import common.lib.models.dto.request.PersonDtoRequest;
import common.lib.models.dto.response.PersonDtoResponse;
import common.lib.models.dto.response.PersonDtoResponseWithToken;
import org.springframework.stereotype.Component;

@Component
public class PersonDtoFactory {
    public PersonDtoResponse toPersonDtoResponse(Person entity) {

        return PersonDtoResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .age(entity.getAge())
                .dateOfBirth(entity.getDateOfBirth())
                .email(entity.getEmail())
                .build();
    }

    public PersonDtoResponseWithToken toPersonDtoResponseWithToken(PersonDtoResponse entity, String token) {

        return PersonDtoResponseWithToken.builder()
                .id(entity.getId())
                .name(entity.getName())
                .age(entity.getAge())
                .dateOfBirth(entity.getDateOfBirth())
                .email(entity.getEmail())
                .token(token)
                .build();
    }

    public Person makePerson(PersonDtoRequest person) {
        return Person.builder()
                .name(person.getName())
                .age(person.getAge())
                .dateOfBirth(person.getDateOfBirth())
                .email(person.getEmail())
                .build();
    }
}
