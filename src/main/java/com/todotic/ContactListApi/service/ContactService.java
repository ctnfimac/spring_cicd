package com.todotic.ContactListApi.service;

import com.todotic.ContactListApi.dto.ContactDTO;
import com.todotic.ContactListApi.entity.Contact;
import com.todotic.ContactListApi.respository.ContactRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class ContactService {

    //@Autowired
    private final ContactRepository contactRepository;
    private final ModelMapper mapper;
    private final String uploadDirectory = "uploads";

    public Iterable<Contact> findAll(){
        return contactRepository.findAll();
    }

    public Contact findById(Long id){
        //return contactRepository.findById(id).orElse(null);
        return contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found with id: " + id));
               // .orElseThrow(ResourceNotFoundException::new);// ::new representa al constructor
    }

    @Transactional
    public Contact create(ContactDTO contactDTO) throws IOException {
        /*String fileName = System.currentTimeMillis() + "_" + contactDTO.getFile().getOriginalFilename();
        Path filePath = Path.of(uploadDirectory, fileName);

        Files.createDirectories(filePath.getParent());
        Files.write(filePath, contactDTO.getFile().getBytes());

        Contact contact = mapper.map(contactDTO, Contact.class);
        contact.setCreatedAt(LocalDateTime.now());*/

        //contact.setPathUrl("uploads/" + fileName);
        Contact contact = this.cargoImagen(contactDTO);
        return contactRepository.save(contact);
    }

    public Contact update(Long id, ContactDTO contactDTO) throws IOException {
        Contact contactoFromDb = this.findById(id);

       // ModelMapper mapper = new ModelMapper();
        mapper.map(contactDTO, contactoFromDb);

        contactoFromDb.setName(contactDTO.getName());
        contactoFromDb.setEmail(contactDTO.getEmail());

        Contact contact = this.cargoImagen(contactDTO);
        contactoFromDb.setPathUrl(contact.getPathUrl());
        return contactRepository.save(contactoFromDb);
    }

    public void delete(Long id){
        Contact contactoFromDb = this.findById(id);
        contactRepository.delete(contactoFromDb);
    }

    Contact cargoImagen(ContactDTO contactDTO) throws IOException{
        String fileName = System.currentTimeMillis() + "_" + contactDTO.getFile().getOriginalFilename();
        Path filePath = Path.of(uploadDirectory, fileName);

        Files.createDirectories(filePath.getParent());
        Files.write(filePath, contactDTO.getFile().getBytes());

        Contact contact = mapper.map(contactDTO, Contact.class);
        contact.setCreatedAt(LocalDateTime.now());
        contact.setPathUrl("uploads/" + fileName);
        return contact;
    }
}


