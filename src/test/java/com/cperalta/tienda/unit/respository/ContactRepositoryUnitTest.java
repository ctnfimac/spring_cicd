package com.cperalta.tienda.unit.respository;

import com.cperalta.tienda.entity.Contact;
import com.cperalta.tienda.respository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ContactRepositoryUnitTest {

    @Autowired
    ContactRepository contactRepository;
    // para hacer las pruebas en una base de datos en memoria
    @Autowired
    TestEntityManager testEntityManager;


    @BeforeEach
    void setUp() {
        Contact contact = Contact.builder()
                .name("goku")
                .email("goku@hotmail.com")
                .createdAt(LocalDateTime.now())
                .build();

        testEntityManager.persist(contact);
    }

    @Test
    public void findContactByNameFound(){
        Optional<Contact> contact = contactRepository.findByName("goku");
        assertEquals(contact.get().getName(), "goku");
    }

    @Test
    public void findContactByNameNotFound(){
        Optional<Contact> contact = contactRepository.findByName("picoro");
        assertEquals(contact, Optional.empty());
    }
}