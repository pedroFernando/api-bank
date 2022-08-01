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
public class AccountServiceImplTest {

    private AccountService accountService;
    private AccountRepository accountRepositoryMock;

    private MovementRepository movementRepositoryMock;

    @BeforeEach
    void setUp() {
        this.accountRepositoryMock = Mockito.mock(AccountRepository.class);
        this.movementRepositoryMock = Mockito.mock(MovementRepository.class);
        this.accountService = new AccountServiceImpl(this.accountRepositoryMock, this.movementRepositoryMock);
    }

    @Test
    void getById() {
        long id = 10;
        Account accountEntity = new Account();
        accountEntity.setId(id);
        accountEntity.setNumber("1234567");

        Mockito.when(this.accountRepositoryMock.findById(id)).thenReturn(Optional.of(accountEntity));
        AccountDto dto = this.accountService.getById(id);

        assertNotNull(dto);
        assertEquals(10, dto.getId());
    }

    @Test
    void save() {
        AccountDto accountDto = AccountDto
                .builder()
                .id(1l)
                .number("1234567")
                .build();

        Account account = Account
                .builder()
                .id(1l)
                .number("1234567")
                .build();

        Mockito.when(accountRepositoryMock.save(Mockito.any(Account.class))).thenReturn(account);
        AccountDto dto = this.accountService.create(accountDto);

        assertNotNull(dto);
        assertEquals(account.getNumber(), dto.getNumber());
    }

}
