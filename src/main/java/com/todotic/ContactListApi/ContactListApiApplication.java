package com.todotic.ContactListApi;

import com.todotic.ContactListApi.entity.Contact;
import com.todotic.ContactListApi.respository.ContactRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ContactListApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactListApiApplication.class, args);
	}

	/*@Bean
	CommandLineRunner runner(ContactRepository contactRepository){
		return args -> {
			// inserto registros de prueba
			List<Contact> contacts = Arrays.asList(
					new Contact("Carlos", "carlos@hotmail.com", LocalDateTime.now()),
					new Contact("Juan", "juan@hotmail.com", LocalDateTime.now()),
					new Contact("Camello", "camello@hotmail.com", LocalDateTime.now()),
					new Contact("Luis", "luis@hotmail.com", LocalDateTime.now()),
					new Contact("Trump", "trump@hotmail.com", LocalDateTime.now())
			);
			contactRepository.saveAll(contacts);
		};
	}*/

	@Bean
	ModelMapper modelMapper(){
		// con esta bean hago inyectable a modelmapper
		return new ModelMapper();
	}

}
