package com.alkemy.ong.domain.contacts;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ContactModel {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String message;
    private boolean deleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}