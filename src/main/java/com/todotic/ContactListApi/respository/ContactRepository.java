package com.todotic.ContactListApi.respository;

import com.todotic.ContactListApi.entity.Contact;

/*
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Integer> {

}*/

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
