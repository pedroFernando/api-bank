package com.pfar.apibank.repository;

import com.pfar.apibank.domain.Account;
import com.pfar.apibank.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByClient(Client client);

    Optional<Account> findByNumber(String number);

}
