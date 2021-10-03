package com.example.redcollar1.models.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    private Long id;
    private String name;
    private Long age;
    private LocalDate dateOfBirth;
    private String email;
    private String login;
    private String pass;
    private List<Long> idContents;
}
