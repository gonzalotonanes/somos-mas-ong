package com.alkemy.ong.web.dto;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class ContactDto {
    @NotEmpty(message = "Name field is required")
    private String name;

    @NotEmpty(message = "Phone field is required")
    private String phone;

    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 10, max = 200, message = "Message must be between 10 and 200 characters")
    private String message;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}