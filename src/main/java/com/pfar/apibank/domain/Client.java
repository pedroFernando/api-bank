package com.pfar.apibank.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@Entity(name = "client")
@SuperBuilder
public class Client extends Person {

    @Size(min=4, message = "Password should have at least 4 characters")
    private String password;
    @NotNull
    private Boolean state;

    @OneToMany(mappedBy = "client")
    private List<Account> accounts;

    public Client() {
        super();
    }

}
