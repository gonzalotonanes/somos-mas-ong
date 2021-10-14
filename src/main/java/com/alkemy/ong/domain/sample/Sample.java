package com.alkemy.ong.domain.sample;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class Sample {
    private Long id;

    @Min(5)
    private String name;

    private String description;
}
