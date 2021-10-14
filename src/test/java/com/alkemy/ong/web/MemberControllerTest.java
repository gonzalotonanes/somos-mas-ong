package com.alkemy.ong.web;

import com.alkemy.ong.database.entities.MemberEntity;
import com.alkemy.ong.database.jparepositories.MemberJPARepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberJPARepository repository;

    public ObjectWriter mapperConfig(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow;
    }

    @Test
    public void getMembers() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        Page<MemberEntity> paginatedMembers = new PageImpl(singletonList(buildMemberEntity()), pageable, 1L);

        when(repository.findAll(pageable)).thenReturn(paginatedMembers);
        mockMvc.perform(get("/members?page=0"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapperConfig().writeValueAsString(buildPageDto())));
    }

    @Test
    public void deleteMember() throws Exception {
        MemberEntity member = buildMemberEntity();
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(new MemberEntity()));
        mockMvc.perform(delete("/members/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void updateMember() throws Exception {
        when(repository.findById(1L)).thenReturn(Optional.of(new MemberEntity()));
        mockMvc.perform(put("/members/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapperConfig().writeValueAsString(buildMemberEntity())))
                .andExpect(status().isOk());
    }

    @Test
    public void createMember() throws Exception {
        mockMvc.perform(post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapperConfig().writeValueAsString(buildMemberEntity())))
                .andExpect(status().isCreated());
    }

    private MemberEntity buildMemberEntity() {
        MemberEntity member = new MemberEntity();

        member.setId(1L);
        member.setName("Agustina");
        member.setFacebookUrl("facebook.com");
        member.setInstagramUrl("instagram.com");
        member.setLinkedinUrl("linkedin.com");
        member.setImage("image1.png");
        member.setDescription("description");

        return member;
    }

    private PageDto buildPageDto(){

        PageDto pageDto = new PageDto();
        pageDto.setContent(singletonList(buildMemberEntity()));
        pageDto.setPreviousPage(null);
        pageDto.setNextPage(null);

        return pageDto;

    }
}