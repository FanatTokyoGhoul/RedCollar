package com.example.redcollar1.repository;

import com.example.redcollar1.models.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
