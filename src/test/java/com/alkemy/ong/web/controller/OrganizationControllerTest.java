package com.alkemy.ong.web.controller;

import com.alkemy.ong.database.entities.OrganizationEntity;
import com.alkemy.ong.database.jparepositories.OrganizationJPARepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrganizationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrganizationJPARepository repository;

    @Autowired
    ObjectMapper mapper;

    private OrganizationEntity buildOrganization() {
        OrganizationEntity organizationEntity = new OrganizationEntity();
        organizationEntity.setId(1);
        organizationEntity.setName("Test Name");
        organizationEntity.setImage("Test img");
        organizationEntity.setAddress("Test address");
        organizationEntity.setPhone(123);
        organizationEntity.setEmail("test@test.com");
        organizationEntity.setWelcomeText("Test welcome");
        organizationEntity.setAboutUsText("Test about");
        organizationEntity.setCreatedAt(LocalDateTime.now());
        organizationEntity.setUpdatedAt(LocalDateTime.now());
        organizationEntity.setDeleted(false);
        organizationEntity.setUrlFacebook("https://www.redsocial.com/user");
        organizationEntity.setUrlLinkedin("https://www.redsocial.com/user");
        organizationEntity.setUrlInstagram("https://www.redsocial.com/user");

        return organizationEntity;
    }

    private List<OrganizationEntity> buildList() {
        List<OrganizationEntity> organizationEntities = new ArrayList<>();
        organizationEntities.add(buildOrganization());
        return organizationEntities;
    }

    @Test
    void getAll() throws Exception {
        List<OrganizationEntity> organizationEntities = buildList();

        when(repository.findAll()).thenReturn(organizationEntities);

        mockMvc.perform(get("/organization/public")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].urlFacebook", is("https://www.redsocial.com/user")))
                .andExpect(jsonPath("$[0].urlLinkedin", is("https://www.redsocial.com/user")))
                .andExpect(jsonPath("$[0].urlInstagram", is("https://www.redsocial.com/user")));
    }

    @Test
    // @WithMockUser(username = "userDeveloper@test.com", authorities = {"ROLE_ADMIN"}) //Cuenta de Admin.
     void idNotExist() throws Exception {
        when(repository.findById(15)).thenReturn(Optional.empty());
        mockMvc.perform(get("/organization/public/15"))
                .andExpect(status().isNotFound());
    }

    @Test
    // @WithMockUser(username = "userDeveloper@test.com", authorities = {"ROLE_ADMIN"}) //Cuenta de Admin.
    void idOk() throws Exception {
        when(repository.findById(1)).thenReturn(Optional.of(buildOrganization()));
        mockMvc.perform(get("/organization/public")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
