package com.alkemy.ong.domain.contacts;

import com.alkemy.ong.web.dto.ContactDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DefaultContactService implements ContactService {

    private ContactRepo contactRepo;

    public DefaultContactService(ContactRepo contactRepo) {
        this.contactRepo = contactRepo;
    }

    @Override
    public List<ContactModel> getContacts() {
        return contactRepo.getContacts();
    }

    public ContactDto createContact(ContactDto contactDto){
        return contactRepo.createContact(contactDto);
    }
}