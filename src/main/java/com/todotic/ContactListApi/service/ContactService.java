package com.todotic.ContactListApi.service;

import com.todotic.ContactListApi.dto.ContactDTO;
import com.todotic.ContactListApi.entity.Contact;
import java.util.Optional;


public interface ContactService {

    Iterable<Contact> findAll();
    Contact findById(Long id);
    Contact create(ContactDTO contactDTO);
    Contact update(Long id, ContactDTO contactDTO);
    void delete(Long id);
    Optional<Contact> findContactByNameWithJPQL(String name);
    Optional<Contact> findByName(String name);
}


