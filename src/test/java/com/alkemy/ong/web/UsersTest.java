package com.alkemy.ong.web;

import com.alkemy.ong.database.entities.RoleEntity;
import com.alkemy.ong.database.entities.UserEntity;
import com.alkemy.ong.database.jparepositories.UserJpaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Optional;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UsersTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserJpaRepository repository;

    public ObjectWriter mapperConfig(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow;
    }

    @Test
    void getAllUsers() throws Exception {
        when(repository.findAll()).thenReturn(singletonList(testUserEntity()));
        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].idUser", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("TestName")))
                .andExpect(jsonPath("$[0].lastName", is("Test")))
                .andExpect(jsonPath("$[0].email", is("test@test.com")))
                .andExpect(jsonPath("$[0].password", is("12345678")))
                .andExpect(jsonPath("$[0].deleted", is(false)))
                .andExpect(jsonPath("$[0].photo", is("Photo test")));;
    }

    @Test
    public void testDeleteUser() throws Exception {
        when(repository.findById(1L)).thenReturn(Optional.of(testUserEntity()));
        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testRegisterUser() throws Exception {
        String url = "/users/auth/register";
        String requestJson = mapperConfig().writeValueAsString(registerUser());
        mockMvc.perform(post(url)
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateUser() throws Exception {
        String url = "/users/1";
        String requestJson = mapperConfig().writeValueAsString(updateUser());
        when(repository.findById(1L)).thenReturn(Optional.of(testUserEntity()));
        mockMvc.perform(patch(url)
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk());
    }

    private UserEntity registerUser(){
        UserEntity user = new UserEntity();
        user.setFirstName("Emiliano");
        user.setLastName("Torres");
        user.setEmail("emitorres93@gmail.com");
        user.setPassword("12345678");
        return user;
    }

    private UserEntity updateUser(){
        UserEntity user = new UserEntity();
        user.setIdUser(1L);
        user.setFirstName("Emiliano");
        user.setLastName("Torres");
        user.setEmail("a@gmail.com");
        user.setPassword("12345678");
        return user;
    }

    private RoleEntity testRoleEntity(){
        RoleEntity role = new RoleEntity();
        role.setIdRole(1L);
        role.setName("User");
        role.setDescription("User description");
        role.setCreatedAt(LocalDateTime.now());
        role.setUpdatedAt(LocalDateTime.now());
        return role;
    }

    private UserEntity testUserEntity() {
        UserEntity user = new UserEntity();
        user.setIdUser(1L);
        user.setFirstName("TestName");
        user.setLastName("Test");
        user.setEmail("test@test.com");
        user.setPassword("12345678");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setDeleted(false);
        user.setPhoto("Photo test");
        user.setRoleId(testRoleEntity());
        return user;
    }
}