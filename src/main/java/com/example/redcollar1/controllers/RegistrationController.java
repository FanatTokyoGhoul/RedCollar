package com.example.redcollar1.controllers;

import com.example.redcollar1.services.RegistrationService;
import common.lib.models.dto.request.PersonDtoRequest;
import common.lib.models.dto.response.PersonDtoResponse;
import common.lib.models.dto.response.PersonDtoResponseWithToken;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.OidcKeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.IDToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@RestController
public class RegistrationController {
    private RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/registration")
    public PersonDtoResponseWithToken registration(@RequestBody PersonDtoRequest personDtoRequest){
        return registrationService.registerPerson(personDtoRequest);
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('user')")
    public String me(HttpServletRequest httpRequest){
        Principal principa = httpRequest.getUserPrincipal();
        KeycloakAuthenticationToken principal = (KeycloakAuthenticationToken) httpRequest.getUserPrincipal();
        OidcKeycloakAccount l =  principal.getAccount();
        KeycloakSecurityContext keycloakSecurityContext = l.getKeycloakSecurityContext();
        IDToken idToken = keycloakSecurityContext.getToken();
        String userId = idToken.getPreferredUsername();

        return userId;
    }
}
