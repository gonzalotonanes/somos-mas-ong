package com.alkemy.ong.domain.utils;

import lombok.Data;

@Data
public class Jwt {
    private String token;
    private String bearer = "Bearer";
}