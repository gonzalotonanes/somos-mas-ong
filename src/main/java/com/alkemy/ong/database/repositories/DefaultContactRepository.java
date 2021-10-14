package com.alkemy.ong.database.repositories;

import com.alkemy.ong.database.entities.ContactEntity;
import com.alkemy.ong.database.jparepositories.ContactJpaRepository;
import com.alkemy.ong.domain.contacts.ContactModel;
import com.alkemy.ong.domain.contacts.ContactRepo;
import com.alkemy.ong.web.dto.ContactDto;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DefaultContactRepository implements ContactRepo {

    ContactJpaRepository contactJpaRepository;

    public DefaultContactRepository(ContactJpaRepository contactJpaRepository) {
        this.contactJpaRepository = contactJpaRepository;
    }

    public List<ContactModel> getContacts() {

        return contactJpaRepository.findAll().stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public ContactModel toModel(ContactEntity contactEntity){
        ContactModel contactModel = new ContactModel();

        contactModel.setId(contactEntity.getId());
        contactModel.setCreatedAt(contactEntity.getCreatedAt());
        contactModel.setDeleted(contactEntity.isDeleted());
        contactModel.setUpdatedAt(contactEntity.getUpdatedAt());
        contactModel.setEmail(contactEntity.getEmail());
        contactModel.setMessage(contactEntity.getMessage());
        contactModel.setName(contactEntity.getName());
        contactModel.setPhone(contactEntity.getPhone());

        return contactModel;
    }

    public ContactDto createContact(ContactDto contactDto){
        contactJpaRepository.save(toEntity(contactDto));
        return contactDto;
    }

    public ContactEntity toEntity(ContactDto contactDto){

        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setUpdatedAt(contactDto.getUpdatedAt());
        contactEntity.setCreatedAt(contactDto.getCreatedAt());
        contactEntity.setEmail(contactDto.getEmail());
        contactEntity.setMessage(contactDto.getMessage());
        contactEntity.setName(contactDto.getName());
        contactEntity.setPhone(contactDto.getPhone());

        return contactEntity;
    }
}