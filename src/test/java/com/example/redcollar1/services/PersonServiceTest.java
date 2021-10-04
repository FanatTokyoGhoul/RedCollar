package com.example.redcollar1.services;

import com.example.redcollar1.exception.IncorrectEmailException;
import com.example.redcollar1.exception.IncorrectNameContentException;
import com.example.redcollar1.models.dto.PersonDto;
import com.example.redcollar1.models.entities.Person;
import com.example.redcollar1.models.entities.VideoContent;
import com.example.redcollar1.models.factories.PersonDtoFactory;
import com.example.redcollar1.repository.PersonRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonServiceTest {
    private static final int CORRECT_NUMBER_FOR_FIND_ENTITY = 1;
    private static final int NOT_CORRECT_NUMBER_FOR_FIND_ENTITY = 5;


    private Person modelPerson = new Person(1L, "as", 18L, LocalDate.of(1111, 9,3), "testTest@mail.ru", "testTest", "fewf", new ArrayList<>());
    private PersonDto modelPersonDto = new PersonDto(1L, "as", 18L, LocalDate.of(1111, 9,3), "testTest@mail.ru", "testTest", "fewf");
    private Person modelPersonNullId = new Person(null, "as", 18L, LocalDate.of(1111, 9,3), "testTest@mail.ru", "testTest", "fewf", null);
    private VideoContent modelVideoContentFirst = new VideoContent(1L, "rrrr", "ttt", "image", "it", modelPerson);
    private VideoContent modelVideoContentSecond = new VideoContent(2L, "rrrr", "ttt", "image", "it", modelPerson);
    private List<Person> personList = new ArrayList<>();
    private List<PersonDto> personListDto = new ArrayList<>();

    private PersonRepository personRepository = mock(PersonRepository.class);
    private CheckDataService checkDataService = mock(CheckDataService.class);
    private PersonService personService = new PersonService(personRepository, new PersonDtoFactory(), checkDataService);

    public PersonServiceTest() {
        modelPerson.getContents().add(modelVideoContentFirst);
        modelPerson.getContents().add(modelVideoContentSecond);
        personList.add(modelPerson);
        personListDto.add(modelPersonDto);
    }

    @Test
    public void createPerson_incorrectEmailException(){
        assertThrows(IncorrectEmailException.class, () ->
                personService.save("as", 18L, "testTest", "testTest", "fewf", LocalDate.of(1111, 9,3)));
    }

    @Test
    public void createVideoContent_correctData() {
        when(personRepository.save(modelPersonNullId))
                .thenReturn(modelPerson);
        assertEquals(modelPersonDto, personService.save("as", 18L, "testTest@mail.ru", "testTest", "fewf", LocalDate.of(1111, 9,3)));
    }

    @Test
    public void findPersonWithMoreContentThanANumber(){
        when(personRepository.findAll()).thenReturn(personList);
        assertEquals(personListDto, personService.findPersonWithMoreContentThanANumber(CORRECT_NUMBER_FOR_FIND_ENTITY));
        assertEquals(new ArrayList<>(), personService.findPersonWithMoreContentThanANumber(NOT_CORRECT_NUMBER_FOR_FIND_ENTITY));
    }
}
