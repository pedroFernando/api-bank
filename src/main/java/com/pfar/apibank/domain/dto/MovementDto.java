package com.pfar.apibank.domain.dto;

import com.pfar.apibank.domain.Movement;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MovementDto {
    private Long id;

    private LocalDate date;

    @NotNull(message = "Type is required")
    @Enumerated(EnumType.STRING)
    private Movement.Type type;

    @NotNull(message = "Mount is required")
    private BigDecimal amount;

    private BigDecimal balance;

    @NotNull(message = "ID account is required")
    private Long accountId;

}
