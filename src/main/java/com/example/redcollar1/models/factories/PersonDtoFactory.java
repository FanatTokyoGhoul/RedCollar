package com.example.redcollar1.models.factories;

import com.example.redcollar1.models.dto.PersonDto;
import com.example.redcollar1.models.entities.Person;
import com.example.redcollar1.models.entities.VideoContent;

import java.util.ArrayList;
import java.util.List;

public class PersonDtoFactory {
    public PersonDto makeEmployeeDto(Person entity) {

        List<Long> idList = new ArrayList<>();

        for (VideoContent videoContent: entity.getContents()) {
            idList.add(videoContent.getId());
        }

        return PersonDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .age(entity.getAge())
                .dateOfBirth(entity.getDateOfBirth())
                .email(entity.getEmail())
                .login(entity.getLogin())
                .pass(entity.getPass())
                .idContents(idList)
                .build();
    }
}
