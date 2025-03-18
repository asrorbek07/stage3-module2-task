package com.mjc.school.repository.model.impl;

import com.mjc.school.repository.model.BaseEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AuthorModel implements BaseEntity<Long> {
    //
    private Long id;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;

    @Override
    public Long getId() {
        //
        return id;
    }

    @Override
    public void setId(Long id) {
        //
        this.id = id;
    }

}
