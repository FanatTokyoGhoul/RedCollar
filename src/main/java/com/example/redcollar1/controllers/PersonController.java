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

    @GetMapping("/high/content")
    public List<PersonDto> findPersonWithMoreContentThanANumber(@RequestParam int number) {
        return servicePerson.findPersonWithMoreContentThanANumber(number);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDto create(@RequestBody PersonDto person) {
        return servicePerson.save(person);
    }

    @PutMapping("/{id}")
    public PersonDto update(@PathVariable Long id, @RequestParam String name, @RequestParam Long age,
                            @RequestParam String email, @RequestParam String login,
                            @RequestParam String pass, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws IncorrectNameContentException {
        return servicePerson.update(id, name, age, email, login, pass, date);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        servicePerson.delete(id);
    }
}