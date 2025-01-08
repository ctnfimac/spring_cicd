package com.cperalta.jardineria.service;

import com.cperalta.jardineria.dto.ContactDTO;
import com.cperalta.jardineria.entity.Contact;

import java.util.List;
import java.util.Optional;


public interface ContactService {

    List<Contact> findAll();
    Contact findById(Long id);
    Contact create(ContactDTO contactDTO);
    Contact update(Long id, ContactDTO contactDTO);
    void delete(Long id);
    Optional<Contact> findContactByNameWithJPQL(String name);
    Optional<Contact> findByName(String name);
}


