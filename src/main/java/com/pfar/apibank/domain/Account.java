package com.pfar.apibank.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@SuperBuilder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "Number is required")
    @Size(min=6, message = "Number should have at least 6 characters")
    private String number;
    @NotNull(message = "Type is required")
    @Enumerated(EnumType.STRING)
    private Type type;
    @NotNull(message = "Initial balance is required")
    private BigDecimal initialBalance;
    @NotNull(message = "Date is required")
    private Boolean state;

    @NotNull(message = "Client is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Client client;

    @OneToMany(mappedBy = "account")
    private List<Movement> movements;

    public static enum Type {
        SAVING, CURRENT;
    }

}
