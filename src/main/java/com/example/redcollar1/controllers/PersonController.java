package com.example.redcollar1.controllers;

import com.example.redcollar1.exception.IncorrectNameContentException;
import com.example.redcollar1.models.dto.request.PersonDtoRequest;
import com.example.redcollar1.models.dto.response.PersonDtoResponse;
import com.example.redcollar1.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/people")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping()
    public List<PersonDtoResponse> index() {
        return personService.findAll();
    }

    @GetMapping("/high/content")
    public List<PersonDtoResponse> findPersonWithMoreContentThanANumber(@RequestParam int number) {
        return personService.findPersonWithMoreContentThanANumber(number);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDtoResponse create(@RequestBody PersonDtoRequest person) {
        return personService.save(person);
    }

    @PutMapping("/{id}")
    public PersonDtoResponse update(@PathVariable Long id, @RequestBody PersonDtoRequest person) throws IncorrectNameContentException {
        return personService.update(id, person);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        personService.delete(id);
    }
}