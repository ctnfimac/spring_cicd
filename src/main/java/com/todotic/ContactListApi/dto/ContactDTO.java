package com.todotic.ContactListApi.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ContactDTO {
    private String name;
    private String email;
}
