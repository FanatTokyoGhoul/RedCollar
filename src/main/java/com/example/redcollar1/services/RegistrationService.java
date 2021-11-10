package com.example.redcollar1.services;

import com.example.redcollar1.models.factories.PersonDtoFactory;
import common.lib.models.dto.request.PersonDtoRequest;
import common.lib.models.dto.response.PersonDtoResponse;
import common.lib.models.dto.response.PersonDtoResponseWithToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final PersonService personService;
    private final KeycloakService keycloakService;
    private final PersonDtoFactory personDtoFactory;

    @Autowired
    public RegistrationService(PersonService personService, KeycloakService keycloakService, PersonDtoFactory personDtoFactory) {
        this.personService = personService;
        this.keycloakService = keycloakService;
        this.personDtoFactory = personDtoFactory;
    }

    public PersonDtoResponseWithToken registerPerson(PersonDtoRequest personDtoRequest){
        PersonDtoResponse personDtoResponse = personService.create(personDtoRequest);
        String token = keycloakService.createUser(personDtoRequest, personDtoResponse.getId());
        return personDtoFactory.toPersonDtoResponseWithToken(personDtoResponse, token);
    }
}
