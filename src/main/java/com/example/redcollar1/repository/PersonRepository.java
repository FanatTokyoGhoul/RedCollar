package com.example.redcollar1.repository;

import com.example.redcollar1.models.entities.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface PersonRepository extends CrudRepository<Person, Long> {

}
