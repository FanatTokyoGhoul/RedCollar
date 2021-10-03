package com.example.redcollar1.services;

import com.example.redcollar1.exception.IncorrectNameContentException;
import com.example.redcollar1.models.dto.VideoContentDto;
import com.example.redcollar1.models.entities.Person;
import com.example.redcollar1.models.entities.VideoContent;
import com.example.redcollar1.models.factories.VideoContentDtoFactory;
import com.example.redcollar1.repository.PersonRepository;
import com.example.redcollar1.repository.VideoContentRepository;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VideoContentServiceTest {


    private VideoContentDto modelVideoContentDto= new VideoContentDto(1L, "rrrr", "ttt", "image", "it", 1L);
    private Person modelPerson = new Person(1l, "sss", 18L, LocalDate.of(2001, 3, 9), "zzz", "qqq", "ttt", new ArrayList<>());
    private VideoContent modelVideoContent = new VideoContent(1L, "rrrr", "ttt", "image", "it", modelPerson);
    private VideoContent modelVideoContentNullId = new VideoContent(null, "rrrr", "ttt", "image", "it", modelPerson);

    private VideoContentRepository repVideoContent = mock(VideoContentRepository.class);
    private PersonRepository repPerson = mock(PersonRepository.class);
    private CheckDataService dataService = mock(CheckDataService.class);
    private VideoContentService videoContentService = new VideoContentService(new VideoContentDtoFactory(), repVideoContent, repPerson, dataService);

    public VideoContentServiceTest() {
        modelPerson.getContents().add(modelVideoContent);
    }

    @Test
    public void createVideoContent_incorrectNameContentException(){
        when(repPerson.getById(1L)).thenReturn(modelPerson);
        assertThrows(IncorrectNameContentException.class, () ->
                videoContentService.save("as", "testTest", "testTest", "testTest", 1L));
    }

    @Test
    public void createVideoContent_correctData() {
        when(repPerson.getById(1L)).thenReturn(modelPerson);
        when(repVideoContent.save(modelVideoContentNullId))
                .thenReturn(modelVideoContent);
        assertEquals(modelVideoContentDto, videoContentService.save("rrrr", "ttt", "image", "it", 1L));
    }

    @Test
    public void updateVideoContent_correctData() {
        when(repPerson.getById(1L)).thenReturn(modelPerson);
        when(repVideoContent.save(modelVideoContentNullId))
                .thenReturn(modelVideoContent);
        assertEquals(modelVideoContentDto, videoContentService.save("rrrr", "ttt", "image", "it", 1L));
    }
}
