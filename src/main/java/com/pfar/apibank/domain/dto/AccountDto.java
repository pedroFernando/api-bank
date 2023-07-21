package com.pfar.apibank.domain.dto;

import com.pfar.apibank.domain.Account;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDto {
    private Long id;

    @NotEmpty(message = "Number is required")
    private String number;

    @NotNull(message = "Type is required")
    @Enumerated(EnumType.STRING)
    private Account.Type type;

    private BigDecimal initialBalance;

    @NotNull(message = "State is required")
    private Boolean state;

    @NotNull(message = "ID client is required")
    private Long clientId;
}
