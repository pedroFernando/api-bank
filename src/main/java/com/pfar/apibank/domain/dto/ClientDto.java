package com.pfar.apibank.domain.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
public class ClientDto extends PersonDto{

    @NotEmpty(message = "Password is required")
    private String password;

    @NotNull(message = "State is required")
    private Boolean state;

    public ClientDto() {
        super();
    }

}
