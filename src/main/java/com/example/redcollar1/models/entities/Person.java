package com.example.redcollar1.models.entities;

import common.lib.models.dto.response.PersonDtoResponse;
import common.lib.models.dto.response.PersonDtoResponseWithToken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("person")
public class Person {

    @Id
    private Long id;

    private String name;
    private Long age;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;
    private String email;
    @MappedCollection(idColumn = "id_person")
    private Set<VideoContent> contents;
}
