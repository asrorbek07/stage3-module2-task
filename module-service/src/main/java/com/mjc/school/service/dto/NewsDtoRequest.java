package com.mjc.school.service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NewsDtoRequest {
    //
    private Long id;
    private String content;
    private String title;
    private Long authorId;

}
