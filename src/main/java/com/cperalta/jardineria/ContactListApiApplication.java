package com.cperalta.jardineria;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ContactListApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactListApiApplication.class, args);
	}
	//TODO: Evaluar si hay que borrar este código
	@Bean
	ModelMapper modelMapper(){
		// con esta bean hago inyectable a modelmapper
		return new ModelMapper();
	}

}
