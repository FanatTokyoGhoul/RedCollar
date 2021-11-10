package com.example.redcollar1.services;

import common.lib.models.dto.request.PersonDtoRequest;
import common.lib.services.KeycloakApiService;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;


@Service
public class KeycloakService {

    @Value("${keycloak.realm}")
    private String realm;

    KeycloakApiService keycloakApiService;

    @Autowired
    public KeycloakService(KeycloakApiService keycloakApiService) {
        this.keycloakApiService = keycloakApiService;
    }

    public String createUser(PersonDtoRequest personDtoRequest, Long userIdDb){

        Keycloak keycloak = keycloakApiService.getKeycloakClientAdmin();

        RealmResource realmResource = keycloak.realm(realm);
        UsersResource usersResource = realmResource.users();

        UserRepresentation user = createUserRepresentation(personDtoRequest.getLogin(), userIdDb.toString());
        Response response = usersResource.create(user);

        String userId = CreatedResponseUtil.getCreatedId(response);
        UserResource userResource = usersResource.get(userId);

        userResource.resetPassword(createPass(personDtoRequest.getPass()));

        return keycloakApiService.getAccessToken(personDtoRequest.getLogin(), personDtoRequest.getPass());
    }

    private UserRepresentation createUserRepresentation(String login, String userId){
        UserRepresentation user = new UserRepresentation();
        user.setUsername(login);
        user.setEnabled(true);
        user.setAttributes(Collections.singletonMap("userId", List.of(userId)));
        return user;
    }

    private CredentialRepresentation createPass(String password){
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(password);
        return passwordCred;
    }
}
