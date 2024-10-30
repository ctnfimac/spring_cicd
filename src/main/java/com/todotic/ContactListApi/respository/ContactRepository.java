package com.todotic.ContactListApi.respository;

import com.todotic.ContactListApi.entity.Contact;

/*
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Integer> {

}*/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("SELECT c FROM Contact c WHERE c.name ILIKE :name")
    Optional<Contact> findContactByNameWithJPQL(String name);

    // utiliza la inversion de control
    Optional<Contact> findByName(String name);

    //Optional<Contact> findByNameIgnoreCase(String name);
}
