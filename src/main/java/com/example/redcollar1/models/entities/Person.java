package com.example.redcollar1.models.entities;

import com.example.redcollar1.models.dto.PersonDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("person")
public class Person {

    // PK FK 1t1 1tm mtm group by sort having distinct count (join left right outer inner)
    // index procedure/func


    // flyway
    @Id
    private Long id;

    private String name;
    private Long age;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;
    private String email;
    private String login;
    private String pass;

    @MappedCollection(idColumn = "id_person")
    private Set<VideoContent> contents;

    public PersonDto toDto() {
        return new PersonDto();
    }
}
