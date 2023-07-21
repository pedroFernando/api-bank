package com.pfar.apibank.service.impl;

import com.pfar.apibank.domain.Account;
import com.pfar.apibank.domain.dto.AccountDto;
import com.pfar.apibank.repository.AccountRepository;
import com.pfar.apibank.repository.MovementRepository;
import com.pfar.apibank.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    private AccountService accountService;
    private AccountRepository accountRepositoryMock;

    @BeforeEach
    void setUp() {
        this.accountRepositoryMock = Mockito.mock(AccountRepository.class);
        MovementRepository movementRepositoryMock = Mockito.mock(MovementRepository.class);
        this.accountService = new AccountServiceImpl(this.accountRepositoryMock, movementRepositoryMock);
    }

    @Test
    void getById() {
        long id = 10;
        Account accountEntity = new Account();
        accountEntity.setId(id);
        accountEntity.setNumber("123456");

        Mockito.when(this.accountRepositoryMock.findById(id)).thenReturn(Optional.of(accountEntity));
        AccountDto dto = this.accountService.getById(id);

        assertNotNull(dto);
        assertEquals(10, dto.getId());
    }

}
