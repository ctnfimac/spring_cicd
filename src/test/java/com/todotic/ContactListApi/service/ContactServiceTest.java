package com.todotic.ContactListApi.service;

import com.todotic.ContactListApi.entity.Contact;
import com.todotic.ContactListApi.respository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContactServiceTest {

    @Autowired
    private ContactService contactService;
    @MockBean
    private ContactRepository contactRepository;

    @BeforeEach
    void setUp() {
        Contact contact = Contact.builder()
                .name("Kamisama")
                .email("kami@gmail.com")
                .createdAt(LocalDateTime.now())
                .build();
        Mockito.when(contactRepository.findByName("Kamisama")).thenReturn(Optional.of(contact));

        // retorno vacio si ingreso un nombre que no existe
        Mockito.when(contactRepository.findByName("Incorrecto")).thenReturn(Optional.empty());
    }
    /*
    @Test
    @DisplayName("Prueba de obtencion de un contacto enviando un nombre válido")
    public void findByNameFound(){
        String contactName = "Kamisama";
        Contact contact = contactService.findByName(contactName).orElse(null);
        assert contact != null;
        assertEquals(contactName, contact.getName());
    }

    @Test
    @DisplayName("Prueba de obtencion de un contacto enviando un nombre no válido")
    public void findByNameNotFound(){
        String contactName = "Incorrecto";
        Optional<Contact> contact = contactService.findByName(contactName);
        assertEquals(contact, Optional.empty());
    }*/
}