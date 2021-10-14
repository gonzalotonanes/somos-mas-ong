package com.alkemy.ong.domain.contacts;

import com.alkemy.ong.web.dto.ContactDto;

import java.util.List;

public interface ContactService {
    List<ContactModel> getContacts();
    ContactDto createContact(ContactDto contactDto);
}