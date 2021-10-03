package com.example.redcollar1.configuration;

import com.example.redcollar1.models.factories.VideoContentDtoFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VideoContentDtoFactoryConfiguration {

    @Bean
    public VideoContentDtoFactory videoContentDtoFactory(){
        return new VideoContentDtoFactory();
    }
}
