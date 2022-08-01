package com.pfar.apibank.domain.dto;

import com.pfar.apibank.domain.Movement;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@SuperBuilder
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

    public MovementDto() {
        super();
    }

}
