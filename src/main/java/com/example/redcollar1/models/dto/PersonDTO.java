package com.example.redcollar1.models.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private Long id;
    private String name;
    private Long age;
    private String email;
    private String login;
    private String pass;
}
