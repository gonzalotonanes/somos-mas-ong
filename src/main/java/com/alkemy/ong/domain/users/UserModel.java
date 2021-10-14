package com.alkemy.ong.domain.users;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserModel {
    private long idUser;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String photo;
    private boolean deleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private RoleModel idRole;
}