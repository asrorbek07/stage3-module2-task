package com.mjc.school.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorDtoRequest {
    //
    private Long id;
    private String name;
}
