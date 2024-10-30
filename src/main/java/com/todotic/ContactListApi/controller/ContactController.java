package com.todotic.ContactListApi.controller;

import com.todotic.ContactListApi.dto.ContactDTO;
import com.todotic.ContactListApi.entity.Contact;
import com.todotic.ContactListApi.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@AllArgsConstructor
@RestController
//@CrossOrigin
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;

    // para generar este constructor o autowired se usa el annotation @AllArgsConstructor
    /*@Autowired
    private ContactRepository contactRepository; // esto equivale al constructor

    ContactController(ContactService contactService) {
        this.contactService = contactService;
    }*/

    @GetMapping("")
    public Iterable<Contact> list(){
        return contactService.findAll();
    }

    @GetMapping("{id}")
    public Contact get(@PathVariable Long id){
        return contactService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "", method = RequestMethod.POST ,consumes = {"multipart/form-data"})
    public Contact create(@Validated @ModelAttribute ContactDTO contactDTO) throws IOException {
        return contactService.create(contactDTO);
    }

    @PutMapping("{id}")
    @RequestMapping(value = "{id}", method = RequestMethod.PUT, consumes = {"multipart/form-data"})
    public Contact update(@PathVariable Long id,
                          @Validated @ModelAttribute ContactDTO formDTO) throws IOException{
        return contactService.update(id, formDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        contactService.delete(id);
    }

    @GetMapping("/findContactByNameWithJPQL/{name}")
    public Optional<Contact> findContactByNameWithJPQL(@PathVariable String name){
        return contactService.findContactByNameWithJPQL(name);
    }

    @GetMapping("/findByName/{name}")
    public Optional<Contact> findByName(@PathVariable String name){
        return contactService.findByName(name);
    }
}
