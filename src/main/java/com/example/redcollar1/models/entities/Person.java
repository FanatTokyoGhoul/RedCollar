package com.example.redcollar1.models.entities;

import com.example.redcollar1.models.dto.PersonDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person {

    // PK FK 1t1 1tm mtm group by sort having distinct count (join left right outer inner)
    // index procedure/func


    // flyway
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long age;
    private LocalDate dateOfBirth;
    private String email;
    private String login;
    private String pass;
    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    private List<VideoContent> contents;

    public PersonDto toDto() {
        return new PersonDto();
    }
}
