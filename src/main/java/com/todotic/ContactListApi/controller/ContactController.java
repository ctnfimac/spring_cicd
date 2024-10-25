package com.todotic.ContactListApi.controller;

import com.todotic.ContactListApi.dto.ContactDTO;
import com.todotic.ContactListApi.entity.Contact;
import com.todotic.ContactListApi.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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
    @PostMapping("")
    public Contact create(@Validated @RequestBody ContactDTO contactDTO){
        return contactService.create(contactDTO);
    }

    @PutMapping("{id}")
    public Contact update(@PathVariable Long id,
                          @Validated @RequestBody ContactDTO formDTO){
        return contactService.update(id, formDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        contactService.delete(id);
    }
}
