package com.alkemy.ong.domain.slides;

import com.alkemy.ong.database.repositories.DefaultSlideRepository;
import com.alkemy.ong.domain.contacts.DomainException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ SpringBootTest
class DefaultSlideServiceTest {

    @Autowired
    DefaultSlideService service;

    @MockBean
    DefaultSlideRepository repository;

    @Test
    void serviceGetDetailsThrowsExceptionWorks() {
        when(repository.getById(10)).thenReturn(null);
        assertThrows(DomainException.class, () -> service.getSlide(10));
    }
}