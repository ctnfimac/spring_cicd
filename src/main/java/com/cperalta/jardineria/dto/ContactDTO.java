package com.cperalta.jardineria.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ContactDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @Email(message = "El email es inválido")
    @NotBlank(message = "El email es obligatorio")
    private String email;
    private MultipartFile file;
}
