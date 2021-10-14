package com.alkemy.ong.web;

import com.alkemy.ong.database.entities.TestimonialEntity;
import com.alkemy.ong.database.jparepositories.TestimonialJpaRepository;
import com.alkemy.ong.web.dto.PageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class TestimonialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TestimonialJpaRepository repository;

    ObjectMapper mapper =
            new ObjectMapper().registerModule(new JavaTimeModule())
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    public ObjectWriter mapperConfig(){
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow;
    }

    @WithMockUser(authorities = "Admin")
    @Test
    void create() throws Exception{
        this.mockMvc.perform(post("/testimonials")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapperConfig().writeValueAsString(buildTestimonialEntity())))
                .andExpect(status().isCreated());
    }

    @WithMockUser(authorities = "Admin")
    @Test
    void update() throws Exception{
        when(repository.findById(1L)).thenReturn(Optional.of(buildTestimonialEntity()));
        mockMvc.perform(put("/testimonials/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapperConfig().writeValueAsString(buildTestimonialEntity())))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @WithMockUser(authorities = "Admin")
    @Test
    void deleteTestimonial() throws Exception{
        when(repository.findById(1L)).thenReturn(Optional.of(buildTestimonialEntity()));
        mockMvc.perform(delete("/testimonials/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void findAllTestimonials() throws Exception{
        Pageable pageable = PageRequest.of(0, 10);
        Page<TestimonialEntity> paginatedTestimonials = new PageImpl(singletonList(buildTestimonialEntity()), pageable, 1L);

        when(repository.findAll(pageable)).thenReturn(paginatedTestimonials);
        mockMvc.perform(get("/testimonials?page=0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapperConfig().writeValueAsString(buildPageDto())));
    }

    private TestimonialEntity buildTestimonialEntity(){
        TestimonialEntity testimonial = new TestimonialEntity();
        testimonial.setId(1L);
        testimonial.setName("Name 1");
        testimonial.setImage("Image 1");
        testimonial.setContent("Content 1");
        testimonial.setDeleted(0);
        testimonial.setCreatedAt(LocalDateTime.of(2021 , Month.SEPTEMBER, 24,20,05,29, 863332));
        testimonial.setUpdatedAt(LocalDateTime.of(2021 , Month.SEPTEMBER, 24,20,05,29, 863332));

        return testimonial;
    }

    private PageDto buildPageDto(){

        PageDto pageDto = new PageDto();
        pageDto.setContent(singletonList(buildTestimonialEntity()));
        pageDto.setPreviousPage(null);
        pageDto.setNextPage(null);
        return pageDto;
    }
}