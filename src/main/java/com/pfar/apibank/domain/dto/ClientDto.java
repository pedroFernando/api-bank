package com.pfar.apibank.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.SuperBuilder;


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
