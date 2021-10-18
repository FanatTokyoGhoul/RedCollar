package com.example.redcollar1.services;

import com.example.redcollar1.exception.IncorrectNameContentException;
import com.example.redcollar1.models.dto.request.VideoContentDtoRequest;
import com.example.redcollar1.models.dto.response.VideoContentDtoResponse;
import com.example.redcollar1.models.entities.Person;
import com.example.redcollar1.models.entities.VideoContent;
import com.example.redcollar1.models.factories.VideoContentDtoFactory;
import com.example.redcollar1.repository.VideoContentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VideoContentServiceTest {

    private final VideoContentDtoRequest modelVideoContentDtoRequestIncorrectName = new VideoContentDtoRequest("rr", "ttt", "image", "it", 1L);
    private final VideoContentDtoRequest modelVideoContentDtoRequest = new VideoContentDtoRequest("rrrr", "ttt", "image", "it", 1L);
    private final VideoContentDtoResponse modelVideoContentDto= new VideoContentDtoResponse(1L, "rrrr", "ttt", "image", "it", 1L);
    private final Person modelPerson = new Person(1l, "sss", 18L, LocalDate.of(2001, 3, 9), "zzz", "qqq", "ttt", new HashSet<>());
    private final VideoContent modelVideoContent = new VideoContent(1L, "rrrr", "ttt", "image", "it", 1L);
    private final VideoContent modelVideoContentNullId = new VideoContent(null, "rrrr", "ttt", "image", "it", 1L);

    private final VideoContentRepository repVideoContent = mock(VideoContentRepository.class);
    private final VideoContentService videoContentService = new VideoContentService(new VideoContentDtoFactory(), repVideoContent);

    @BeforeEach
    void init() {
        modelPerson.getContents().add(modelVideoContent);
    }

    @Test
    public void createVideoContent_incorrectNameContentException(){
        assertThrows(IncorrectNameContentException.class, () ->
                videoContentService.create(modelVideoContentDtoRequestIncorrectName));
    }

    @Test
    public void createVideoContent_correctData() {
        when(repVideoContent.save(modelVideoContentNullId))
                .thenReturn(modelVideoContent);
        assertEquals(modelVideoContentDto, videoContentService.create(modelVideoContentDtoRequest));
    }

    @Test
    public void updateVideoContent_correctData() {
        when(repVideoContent.save(modelVideoContentNullId))
                .thenReturn(modelVideoContent);
        assertEquals(modelVideoContentDto, videoContentService.create(modelVideoContentDtoRequest));
    }
}
