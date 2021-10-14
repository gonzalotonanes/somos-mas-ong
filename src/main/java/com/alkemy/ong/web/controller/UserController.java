package com.alkemy.ong.web.controller;

import com.alkemy.ong.domain.users.UserModel;
import com.alkemy.ong.domain.users.UserService;
import com.alkemy.ong.domain.utils.Jwt;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping
    public ResponseEntity<List<UserModel>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long idUser, @Valid @RequestBody UserDto userDto) {
        checkExistence(idUser);
        return ResponseEntity.ok(toDto(userService.updateUser(toModel(userDto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long idUser) {
        userService.deleteUser(idUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<Jwt> registerUserAccount(@Valid @RequestBody UserRegisterDto userRegisterDto){
        return ResponseEntity.ok(userService.registerUserAccount(toModelRegister(userRegisterDto)));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<UserDtoLogin> loginUser(@Valid @RequestBody LoginDto loginDto){
       return ResponseEntity.ok(toDtoLogin(userService.loginUser(toModelLogin(loginDto))));
    }

    @GetMapping("/auth/me")
    public ResponseEntity<ProfileDto> profile(){
        return ResponseEntity.ok(toDtoProfile(userService.profile()));
    }

    private void checkExistence(Long id) {
        userService.getUserById(id);
    }

    @Data
    @AllArgsConstructor
    private static class LoginDto{
        @ApiModelProperty(required = true)
        @Email(message = "Email should be valid")
        private String email;

        @ApiModelProperty(required = true)
        @Size(min = 4, max = 8, message = "password must be between 4 and 8 characters")
        private String password;
    }

    private UserModel toModelLogin(LoginDto loginDto){
        UserModel user = new UserModel();
        user.setEmail(loginDto.getEmail());
        user.setPassword(loginDto.getPassword());
        return user;
    }

    @Data
    private class ProfileDto{
        private String firstName;
        private String lastName;
        private String email;
        private String photo;
    }

    private ProfileDto toDtoProfile(UserModel userModel){
        ProfileDto profile = new ProfileDto();
        profile.setFirstName(userModel.getFirstName());
        profile.setLastName( userModel.getLastName());
        profile.setEmail( userModel.getEmail());
        profile.setPhoto( userModel.getPhoto());
        return profile;
    }

    @Data
    private static class UserRegisterDto{
        @ApiModelProperty(required = true)
        @NotBlank(message = "Name field is required")
        private String firstName;

        @ApiModelProperty(required = true)
        @NotBlank(message = "Name field is required")
        private String lastName;

        @ApiModelProperty(required = true)
        @Email(message = "Email should be valid")
        private String email;

        @ApiModelProperty(required = true)
        @Size(min = 4, max = 8, message = "password must be between 4 and 8 characters")
        private String password;
    }

    private UserModel toModelRegister(UserRegisterDto userRegisterDto){
        UserModel user = new UserModel();
        user.setFirstName(userRegisterDto.getFirstName());
        user.setLastName(userRegisterDto.getLastName());
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(userRegisterDto.getPassword());
        return user;
    }

    @Data
    private static class UserDto{
        private Long idUser;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String photo;
        private LocalDateTime updatedAt;
    }

    @Data
    private static class UserDtoLogin{
        private Long idUser;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String role;
    }

    private UserDtoLogin toDtoLogin(UserModel userModel){
        UserDtoLogin user = new UserDtoLogin();
        user.setIdUser(userModel.getIdUser());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setEmail(userModel.getEmail());
        user.setPassword(userModel.getPassword());
        user.setRole(userModel.getIdRole().getName());
        return user;
    }

    private UserModel toModel(UserDto userDto){
        UserModel user = new UserModel();
        user.setIdUser(userDto.getIdUser());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUpdatedAt(userDto.getUpdatedAt());
        user.setPhoto(userDto.getPhoto());
        return user;
    }

    private UserDto toDto(UserModel userModel){
        UserDto userDto = new UserDto();
        userDto.setIdUser(userModel.getIdUser());
        userDto.setFirstName(userModel.getFirstName());
        userDto.setLastName(userModel.getLastName());
        userDto.setEmail(userModel.getEmail());
        userDto.setPassword(userModel.getPassword());
        userDto.setPhoto(userModel.getPhoto());
        userDto.setUpdatedAt(userModel.getUpdatedAt());
        return userDto;
    }
}