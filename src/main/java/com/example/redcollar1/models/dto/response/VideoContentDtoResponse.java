package com.example.redcollar1.models.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoContentDtoResponse {

    private Long id;
    private String name;
    private String genres;
    private String image;
    private String description;
    private Long idPerson;
}

