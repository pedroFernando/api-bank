package com.pfar.apibank.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.SuperBuilder;



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

}
