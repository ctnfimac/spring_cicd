package com.cperalta.tienda.service;

import com.cperalta.tienda.dto.ContactDTO;
import com.cperalta.tienda.entity.Contact;
import com.cperalta.tienda.respository.ContactRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {

    //@Autowired
    private final ContactRepository contactRepository;
    private final ModelMapper mapper;
    private final String uploadDirectory = "uploads";

    public List<Contact> findAll(){
        return contactRepository.findAll();
    }

    public Contact findById(Long id){
        //return contactRepository.findById(id).orElse(null);
        return contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found with id: " + id));
        // .orElseThrow(ResourceNotFoundException::new);// ::new representa al constructor
    }

    @Transactional
    public Contact create(ContactDTO contactDTO){
        Contact contact_new = null;
        try{
            Contact contact = this.cargoImagen(contactDTO);
            contact_new = contactRepository.save(contact);
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        return contact_new;
    }

    public Contact update(Long id, ContactDTO contactDTO){
        Contact contact_update = null;
        try{
            Contact contactoFromDb = this.findById(id);

            // ModelMapper mapper = new ModelMapper();
            mapper.map(contactDTO, contactoFromDb);

            contactoFromDb.setName(contactDTO.getName());
            contactoFromDb.setEmail(contactDTO.getEmail());

            Contact contact = this.cargoImagen(contactDTO);
            contactoFromDb.setPathUrl(contact.getPathUrl());
            contact_update = contactRepository.save(contactoFromDb);
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        return contact_update;
    }

    public void delete(Long id){
        Contact contactoFromDb = this.findById(id);
        contactRepository.delete(contactoFromDb);
    }

    public Optional<Contact> findContactByNameWithJPQL(String name){
        return contactRepository.findContactByNameWithJPQL(name);
    }

    public Optional<Contact> findByName(String name){
        return contactRepository.findByName(name);
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


