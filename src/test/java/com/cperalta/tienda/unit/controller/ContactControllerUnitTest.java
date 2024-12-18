package com.cperalta.tienda.unit.controller;

import com.cperalta.tienda.config.NoSecurityConfig;
import com.cperalta.tienda.controller.ContactController;
import com.cperalta.tienda.entity.Contact;
import com.cperalta.tienda.service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContactController.class)
@Import(NoSecurityConfig.class)
class ContactControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactService contactService;

    private Contact contact;
    private Contact contact2;
    private List<Contact> contacts;

    @BeforeEach
    void setUp() {
        LocalDateTime fechaEspecifica = LocalDateTime.of(2023, 12, 25, 10, 30, 0);
        this.contact = Contact.builder()
                .id(1)
                .name("Christian")
                .email("chris@hotmail.com")
                .createdAt(fechaEspecifica)
                .build();

        this.contact2 = Contact.builder()
                .id(2)
                .name("Ricardo")
                .email("ricky@hotmail.com")
                .createdAt(fechaEspecifica)
                .build();

        this.contacts = Arrays.asList(this.contact, this.contact2);
    }

    @Test
    public void findContactById() throws Exception{
        Mockito.when(contactService.findById(1L)).thenReturn(this.contact);
        mockMvc.perform(get("/api/contacts/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //.andExpect(jsonPath("$.name").value(contact.getName()))
        //.andExpect(jsonPath("$.email").value(contact.getEmail()));
    }


    @Test
    public void contactList() throws Exception{
        Mockito.when(contactService.findAll()).thenReturn(this.contacts);

        mockMvc.perform(get("/api/contacts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Christian")))
                .andExpect(jsonPath("$[0].email", is("chris@hotmail.com")))
                .andExpect(jsonPath("$[1].email", is("ricky@hotmail.com")))
                .andExpect(jsonPath("$[1].name", is("Ricardo")));
    }
}