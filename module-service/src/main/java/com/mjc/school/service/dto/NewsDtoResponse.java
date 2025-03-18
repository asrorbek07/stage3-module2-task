package com.mjc.school.service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NewsDtoResponse {
    //
    private Long id;
    private String content;
    private String title;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Long authorId;

}
