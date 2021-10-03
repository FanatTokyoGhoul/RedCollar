package com.example.redcollar1.controllers;

import com.example.redcollar1.exception.IncorrectNameContentException;
import com.example.redcollar1.models.dto.PersonDto;
import com.example.redcollar1.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/people")
public class PersonController {

    @Autowired
    private PersonService servicePerson;

    @GetMapping()
    public List<PersonDto> index() {
        return servicePerson.findAll();
    }

    @GetMapping("/content")
    public List<PersonDto> findPersonWithMoreContentThanANumber(@RequestParam int number) {
        return servicePerson.findPersonWithMoreContentThanANumber(number);
    }

    @PutMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDto create(@RequestParam String name, @RequestParam Long age, @RequestParam String email,
                            @RequestParam String login, @RequestParam String pass,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth) {

        return servicePerson.save(name, age, email, login, pass, dateOfBirth);
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam Long id) throws IncorrectNameContentException {
        servicePerson.delete(id);
        return "Deleted";
    }
}