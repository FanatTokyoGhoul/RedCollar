package com.example.redcollar1.services;

import com.example.redcollar1.exception.IncorrectNameContentException;
import com.example.redcollar1.models.dto.request.VideoContentDtoRequest;
import com.example.redcollar1.models.dto.response.VideoContentDtoResponse;
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

    private VideoContentDtoRequest modelVideoContentDtoRequestIncorrectName = new VideoContentDtoRequest("rr", "ttt", "image", "it", 1L);
    private VideoContentDtoRequest modelVideoContentDtoRequest = new VideoContentDtoRequest("rrrr", "ttt", "image", "it", 1L);
    private VideoContentDtoResponse modelVideoContentDto= new VideoContentDtoResponse(1L, "rrrr", "ttt", "image", "it", 1L);
    private Person modelPerson = new Person(1l, "sss", 18L, LocalDate.of(2001, 3, 9), "zzz", "qqq", "ttt", new HashSet<>());
    private VideoContent modelVideoContent = new VideoContent(1L, "rrrr", "ttt", "image", "it", 1L);
    private VideoContent modelVideoContentNullId = new VideoContent(null, "rrrr", "ttt", "image", "it", 1L);

    private VideoContentRepository repVideoContent = mock(VideoContentRepository.class);
    private PersonRepository repPerson = mock(PersonRepository.class);
    private VideoContentService videoContentService = new VideoContentService(new VideoContentDtoFactory(), repVideoContent, repPerson);

    public VideoContentServiceTest() {
        modelPerson.getContents().add(modelVideoContent);
    }

    @Test
    public void createVideoContent_incorrectNameContentException(){
        assertThrows(IncorrectNameContentException.class, () ->
                videoContentService.save(modelVideoContentDtoRequestIncorrectName));
    }

    @Test
    public void createVideoContent_correctData() {
        when(repVideoContent.save(modelVideoContentNullId))
                .thenReturn(modelVideoContent);
        assertEquals(modelVideoContentDto, videoContentService.save(modelVideoContentDtoRequest));
    }

    @Test
    public void updateVideoContent_correctData() {
        when(repPerson.findById(1L)).thenReturn(Optional.of(modelPerson));
        when(repVideoContent.save(modelVideoContentNullId))
                .thenReturn(modelVideoContent);
        assertEquals(modelVideoContentDto, videoContentService.save(modelVideoContentDtoRequest));
    }
}
