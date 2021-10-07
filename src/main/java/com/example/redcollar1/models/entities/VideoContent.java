package com.example.redcollar1.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("content")
public class VideoContent {
    @Id
    private Long id;

    private String name;
    private String genres;
    private String image;
    private String description;
    private Long idPerson;
}
