package com.cperalta.tienda.service;

import com.cperalta.tienda.entity.Contact;
import com.cperalta.tienda.respository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @InjectMocks
    private ContactServiceImpl contactService;
    @Mock
    private ContactRepository contactRepository;

    private Contact contact;

    @BeforeEach
    void setUp() {
        contact = Contact.builder()
                .name("Kamisama")
                .email("kami@gmail.com")
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Test
    @DisplayName("Prueba de obtencion de un contacto enviando un nombre válido")
    public void findByNameFound(){
        Mockito.when(contactRepository.findByName("Kamisama")).thenReturn(Optional.of(this.contact));
        String contactName = "Kamisama";
        Contact contact = contactService.findByName(contactName).orElseThrow(
                () -> new AssertionError("El contacto no fue encontrado")
        );
        assertEquals(contactName, contact.getName());
    }

    @Test
    @DisplayName("Prueba de obtencion de un contacto enviando un nombre no válido")
    public void findByNameNotFound(){
        // retorno vacio si ingreso un nombre que no existe
        Mockito.when(contactRepository.findByName("Incorrecto")).thenReturn(Optional.empty());
        String contactName = "Incorrecto";
        Optional<Contact> contact = contactService.findByName(contactName);
        assertEquals(contact, Optional.empty());

    }
}