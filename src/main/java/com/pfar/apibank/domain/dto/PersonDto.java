package com.pfar.apibank.domain.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
public class PersonDto {

    private Long id;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Gender is required")
    private String gender;

    @NotNull(message = "Age is required")
    private Integer age;

    @NotEmpty(message = "DNI is required")
    private String dni;

    @NotEmpty(message = "Address is required")
    private String address;

    @NotEmpty(message = "Phone is required")
    private String phone;

    public PersonDto() {
        super();
    }

    public PersonDto(String name, String gender, Integer age, String dni, String address, String phone) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.dni = dni;
        this.address = address;
        this.phone = phone;
    }

}
