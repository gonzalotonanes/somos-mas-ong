package com.alkemy.ong.domain.users;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class RoleModel {
    private Long idRole;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<UserModel> users;
}