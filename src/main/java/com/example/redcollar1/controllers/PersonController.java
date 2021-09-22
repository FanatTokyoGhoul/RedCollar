package com.example.redcollar1.controllers;

import com.example.redcollar1.exception.ValidateEmailException;
import com.example.redcollar1.models.entities.Person;
import com.example.redcollar1.repository.PersonRepository;
import com.example.redcollar1.services.DataServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/people")
public class PersonController {

    private final DataServiceJPA dataServiceJPA;

    @Autowired
    public PersonController(DataServiceJPA dataServiceJPA) {
        this.dataServiceJPA = dataServiceJPA;
    }

    @GetMapping()
    public List<Person> index() {
        return dataServiceJPA.findAll();
    }

    @PostMapping("/new")
    public String create(@RequestParam String name, @RequestParam Long age, @RequestParam String email,
                         @RequestParam String login, @RequestParam String pass) {

        try {
            dataServiceJPA.save(name, age, email, login, pass);
        } catch (ValidateEmailException e) {
            e.printStackTrace();
            return "Not write. Bad email";
        }
        return "write";
    }
}