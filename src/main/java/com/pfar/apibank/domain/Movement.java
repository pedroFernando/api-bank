package com.pfar.apibank.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@Entity(name = "movement")
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Type is required")
    @Enumerated(EnumType.STRING)
    private Type type;

    @NotNull(message = "Amount is required")
    private BigDecimal amount;

    @NotNull(message = "Balance is required")
    private BigDecimal balance;

    @NotNull(message = "Account is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Account account;

    public Movement() {
        super();
    }

    public static enum Type {
        DEBIT, CREDIT;
    }

}
