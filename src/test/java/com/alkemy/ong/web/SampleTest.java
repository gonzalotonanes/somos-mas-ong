package com.alkemy.ong.web;

import com.alkemy.ong.database.entities.SampleEntity;
import com.alkemy.ong.database.jparepositories.SampleJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SampleTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SampleJpaRepository repository;


    /*@Test
    public void shouldReturnAllSamples() throws Exception {
        when(repository.findAll()).thenReturn(singletonList(buildSampleEntity()));
        mockMvc.perform(get("/samples"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"Test Name\",\"description\":\"Test Description\"}]"));
    }*/

    private SampleEntity buildSampleEntity() {
        SampleEntity sample = new SampleEntity();
        sample.setId(1L);
        sample.setName("Test Name");
        sample.setDescription("Test Description");
        return sample;
    }
}
