package com.alkemy.ong.web;

import com.alkemy.ong.database.entities.ContactEntity;
import com.alkemy.ong.database.jparepositories.ContactJpaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ContactControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ContactJpaRepository repository;

    ObjectMapper objectMapper =
            new ObjectMapper().registerModule(new JavaTimeModule())
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    public ObjectWriter mapperConfig(){
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        return ow;
    }

    @WithMockUser(authorities = "Admin")
    @Test
    void getContacts() throws Exception {
        when(repository.findAll()).thenReturn(singletonList(buildContactEntity()));
        mockMvc.perform(get("/contacts"))
                .andExpect(content().json("[{\"id\":1,\"name\":\"Name 1\",\"phone\"" +
                        ":\"Phone 1\"," + "\"email\":\"name1@gmail.com\",\"message\":\"Message with more than 10 characters\"}]"));
    }

    @WithMockUser(authorities = "Admin")
    @Test
    void createContact() throws Exception { 
        mockMvc.perform(post("/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapperConfig().writeValueAsString(buildContactEntity())))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    private ContactEntity buildContactEntity(){
        ContactEntity contact = new ContactEntity();
        contact.setId(1L);
        contact.setName("Name 1");
        contact.setPhone("Phone 1");
        contact.setEmail("name1@gmail.com");
        contact.setMessage("Message with more than 10 characters");
        contact.setCreatedAt(LocalDateTime.now());
        contact.setUpdatedAt(LocalDateTime.now());

        return contact;
    }
}