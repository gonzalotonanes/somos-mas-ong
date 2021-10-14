package com.alkemy.ong.web;

import com.alkemy.ong.database.entities.NewsEntity;
import com.alkemy.ong.database.jparepositories.NewsJPARepository;
import com.alkemy.ong.web.dto.PageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
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
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NewsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NewsJPARepository repository;

    public ObjectWriter mapperConfig(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow;
    }

    @Test
    public void getNews() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        Page<NewsEntity> paginatedMembers = new PageImpl(singletonList(buildNewsEntity()), pageable, 1L);

        when(repository.findAll(pageable)).thenReturn(paginatedMembers);
        mockMvc.perform(get("/news?page=0"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapperConfig().writeValueAsString(buildPageDto())));
    }


    @Test
    public void createNew() throws Exception {
        mockMvc.perform(post("/news")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapperConfig().writeValueAsString(buildNewsEntity())))
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteNew() throws Exception {
        NewsEntity news = buildNewsEntity();
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(new NewsEntity()));
        mockMvc.perform(delete("/news/1"))
                .andExpect(status().isNoContent());
    }

   @Test
   public void updateNew() throws Exception {
       when(repository.findById(1L)).thenReturn(Optional.of(new NewsEntity()));
       mockMvc.perform(put("/news/1")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(mapperConfig().writeValueAsString(buildNewsEntity())))
               .andExpect(status().isOk());
    }


    private NewsEntity buildNewsEntity(){

        NewsEntity entity = new NewsEntity();

        entity.setIdCategory(1);
        entity.setContent("content");
        entity.setId(1L);
        entity.setImage("image.png");
        entity.setName("String name");

        return entity;
    }

    private PageDto buildPageDto(){

        PageDto pageDto = new PageDto();
        pageDto.setContent(singletonList(buildNewsEntity()));
        pageDto.setPreviousPage(null);
        pageDto.setNextPage(null);

        return pageDto;
    }
}
