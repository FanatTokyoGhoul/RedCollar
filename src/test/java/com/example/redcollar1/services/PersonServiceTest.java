package com.example.redcollar1.services;

import com.example.redcollar1.exception.IncorrectEmailException;
import com.example.redcollar1.models.dto.request.PersonDtoRequest;
import com.example.redcollar1.models.dto.response.PersonDtoResponse;
import com.example.redcollar1.models.entities.Person;
import com.example.redcollar1.models.entities.VideoContent;
import com.example.redcollar1.models.factories.PersonDtoFactory;
import com.example.redcollar1.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonServiceTest {
    private static final int CORRECT_NUMBER_FOR_FIND_ENTITY = 1;
    private static final int NOT_CORRECT_NUMBER_FOR_FIND_ENTITY = 5;

    private final PersonDtoRequest modelPersonDtoRequestIncorrectEmail = new PersonDtoRequest("as", 18L, LocalDate.of(1111, 9, 3), "testTest", "testTest", "fewf");
    private final PersonDtoRequest modelPersonDtoRequest = new PersonDtoRequest("as", 18L, LocalDate.of(1111, 9, 3), "testTest@mail.ru", "testTest", "fewf");
    private final Person modelPerson = new Person(1L, "as", 18L, LocalDate.of(1111, 9, 3), "testTest@mail.ru", "testTest", "fewf", new HashSet<>());
    private final PersonDtoResponse personDtoResponse = new PersonDtoResponse(1L, "as", 18L, LocalDate.of(1111, 9,3), "testTest@mail.ru", "testTest", "fewf");
    private final Person modelPersonNullId = new Person(null, "as", 18L, LocalDate.of(1111, 9,3), "testTest@mail.ru", "testTest", "fewf", null);
    private final VideoContent modelVideoContentFirst = new VideoContent(1L, "rrrr", "ttt", "image", "it", 1L);
    private final VideoContent modelVideoContentSecond = new VideoContent(2L, "rrrr", "ttt", "image", "it", 1L);
    private final List<Person> personList = new ArrayList<>();
    private final List<PersonDtoResponse> personListDto = new ArrayList<>();

    private final PersonRepository personRepository = mock(PersonRepository.class);
    private final PersonService personService = new PersonService(personRepository, new PersonDtoFactory());

    @BeforeEach
    void init(){
        modelPerson.getContents().add(modelVideoContentFirst);
        modelPerson.getContents().add(modelVideoContentSecond);
        personList.add(modelPerson);
        personListDto.add(personDtoResponse);
    }

    @Test
    public void createPerson_incorrectEmailException(){
        assertThrows(IncorrectEmailException.class, () ->
                personService.create(modelPersonDtoRequestIncorrectEmail));
    }

    @Test
    public void createVideoContent_correctData() {
        when(personRepository.save(modelPersonNullId))
                .thenReturn(modelPerson);
        assertEquals(personDtoResponse, personService.create(modelPersonDtoRequest));
    }

    @Test
    public void findPersonWithMoreContentThanANumber(){
        when(personRepository.findAll()).thenReturn(personList);
        assertEquals(personListDto, personService.findPersonWithMoreContentThanANumber(CORRECT_NUMBER_FOR_FIND_ENTITY));
        assertEquals(new ArrayList<>(), personService.findPersonWithMoreContentThanANumber(NOT_CORRECT_NUMBER_FOR_FIND_ENTITY));
    }
}
