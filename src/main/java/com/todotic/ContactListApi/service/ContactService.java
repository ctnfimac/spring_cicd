package com.todotic.ContactListApi.service;

import com.todotic.ContactListApi.dto.ContactDTO;
import com.todotic.ContactListApi.entity.Contact;
import com.todotic.ContactListApi.exception.ResourceNotFoundException;
import com.todotic.ContactListApi.respository.ContactRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class ContactService {

    //@Autowired
    private final ContactRepository contactRepository;
    private final ModelMapper mapper;

    public Iterable<Contact> findAll(){
        return contactRepository.findAll();
    }

    public Contact findById(Integer id){
        //return contactRepository.findById(id).orElse(null);
        return contactRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);// ::new representa al constructor
    }

    public Contact create(ContactDTO contactDTO){
        //Contact contact = new Contact();
        //ModelMapper mapper = new ModelMapper();
        Contact contact = mapper.map(contactDTO, Contact.class);

        /*contact.setName(contactDTO.getName());
        contact.setEmail(contactDTO.getEmail());*/
        contact.setCreatedAt(LocalDateTime.now());
        return contactRepository.save(contact);
    }

    public Contact update(Integer id, ContactDTO contactDTO){
        Contact contactoFromDb = this.findById(id);

       // ModelMapper mapper = new ModelMapper();
        mapper.map(contactDTO, contactoFromDb);

        contactoFromDb.setName(contactDTO.getName());
        contactoFromDb.setEmail(contactDTO.getEmail());
        return contactRepository.save(contactoFromDb);
    }

    public void delete(Integer id){
        Contact contactoFromDb = this.findById(id);
        contactRepository.delete(contactoFromDb);
    }
}
