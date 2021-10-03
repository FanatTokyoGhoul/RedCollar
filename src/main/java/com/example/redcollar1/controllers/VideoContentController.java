package com.example.redcollar1.controllers;

import com.example.redcollar1.exception.IncorrectNameContentException;
import com.example.redcollar1.models.dto.VideoContentDto;
import com.example.redcollar1.services.VideoContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;
import java.util.List;


@RestController
@RequestMapping("/content")
public class VideoContentController {

    @Autowired
    private VideoContentService serviceContent;


    @GetMapping()
    public List<VideoContentDto> index() {
        return serviceContent.findAll();
    }

    @PutMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public VideoContentDto create(@RequestParam String name, @RequestParam String genres,
                                  @RequestParam String image, @RequestParam String description, @RequestParam Long idPerson) throws IncorrectNameContentException {
        return serviceContent.save(name, genres, image, description, idPerson);
    }

    @PutMapping("/update")
    public VideoContentDto update(@RequestParam Long id, @RequestParam String name, @RequestParam String genres,
                                  @RequestParam String image, @RequestParam String description) throws IncorrectNameContentException {
        return serviceContent.update(id, name, genres, image, description);
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam Long id) throws IncorrectNameContentException {
        serviceContent.delete(id);
        return "Deleted";
    }
}