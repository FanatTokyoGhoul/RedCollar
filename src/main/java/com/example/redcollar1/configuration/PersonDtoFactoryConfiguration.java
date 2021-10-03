package com.example.redcollar1.configuration;

import com.example.redcollar1.models.factories.PersonDtoFactory;
import com.example.redcollar1.models.factories.VideoContentDtoFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonDtoFactoryConfiguration {
    @Bean
    public PersonDtoFactory personDtoFactory(){
        return new PersonDtoFactory();
    }
}
