package com.example.redcollar1.controllers;

import com.example.redcollar1.exception.IncorrectNameContentException;
import com.example.redcollar1.models.dto.request.VideoContentDtoRequest;
import com.example.redcollar1.models.dto.response.VideoContentDtoResponse;
import com.example.redcollar1.services.VideoContentService;
import com.example.redcollar1.services.validation.CheckData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/content")
public class VideoContentController {

    private final VideoContentService serviceContent;

    public VideoContentController(VideoContentService serviceContent) {
        this.serviceContent = serviceContent;
    }

    @GetMapping()
    public List<VideoContentDtoResponse> index() {
        return serviceContent.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VideoContentDtoResponse create(@RequestBody VideoContentDtoRequest videoContentDtoRequest) throws IncorrectNameContentException {
        CheckData.validateNameContent(videoContentDtoRequest.getName());

        return serviceContent.create(videoContentDtoRequest);
    }

    @PutMapping("/{id}")
    public VideoContentDtoResponse update(@PathVariable Long id, @RequestBody VideoContentDtoRequest videoContentDtoRequest) throws IncorrectNameContentException {
        return serviceContent.update(id, videoContentDtoRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        serviceContent.delete(id);
    }
}