package com.pfar.apibank.repository;

import com.pfar.apibank.domain.Account;
import com.pfar.apibank.domain.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

    List<Movement> findByAccount(Account account);

    List<Movement> findByAccountAndDate(Account account, LocalDate date);

    @Query(nativeQuery = true, value = "SELECT TOP 1 * FROM MOVEMENT WHERE ACCOUNT_ID = :accountId ORDER BY DATE, ID DESC")
    Optional<Movement> findTopByAccountOrderByDateAndIdDesc(@Param("accountId") Long accountId);

}
