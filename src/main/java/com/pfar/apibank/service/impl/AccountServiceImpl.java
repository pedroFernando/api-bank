package com.pfar.apibank.service.impl;

import com.pfar.apibank.domain.Account;
import com.pfar.apibank.domain.Client;
import com.pfar.apibank.domain.Movement;
import com.pfar.apibank.domain.dto.AccountDto;
import com.pfar.apibank.exceptions.EntityNotFoundException;
import com.pfar.apibank.repository.AccountRepository;
import com.pfar.apibank.repository.MovementRepository;
import com.pfar.apibank.service.AccountService;
import com.pfar.apibank.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/** {@inheritDoc} */
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final MovementRepository movementRepository;

    /** {@inheritDoc} */
    @Override
    public AccountDto getById(long id) {
        return convertEntityToDto(getEntityById(id));
    }

    /** {@inheritDoc} */
    @Override
    public AccountDto getByNumber(String number) {
        return convertEntityToDto(getEntityByNumber(number));
    }


    /** {@inheritDoc} */
    @Override
    public List<AccountDto> getAll() {
        return this.accountRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public List<AccountDto> getByClient(long idClient) {
        Client client = new Client();
        client.setId(idClient);
        return this.accountRepository.findByClient(client).stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Transactional
    @Override
    public AccountDto create(AccountDto data) {
        data.setInitialBalance(data.getInitialBalance()==null?BigDecimal.ZERO:data.getInitialBalance());
        Account account = convertDtoToEntity(data);
        AccountDto accountDto = convertEntityToDto(this.accountRepository.save(account));
        if (data.getInitialBalance().compareTo(BigDecimal.ZERO) > 0) {
            account.setId(accountDto.getId());
            Movement movement = Movement
                    .builder()
                    .type(Movement.Type.CREDIT)
                    .balance(data.getInitialBalance())
                    .account(account)
                    .date(LocalDate.now())
                    .amount(data.getInitialBalance())
                    .build();
            movementRepository.save(movement);
        }
        return accountDto;
    }

    /** {@inheritDoc} */
    @Override
    public AccountDto update(long id, AccountDto data) {
        Account account = getEntityById(id);
        account.setNumber(data.getNumber());
        account.setType(data.getType());
        account.getClient().setId(data.getClientId());
        account.setState(data.getState());
        return convertEntityToDto(this.accountRepository.save(account));
    }

    /** {@inheritDoc} */
    @Override
    public long deleteById(long id) {
        getEntityById(id);
        this.accountRepository.deleteById(id);
        return id;
    }

    private Account getEntityById(long id) {
        Optional<Account> optional = this.accountRepository.findById(id);
        if(optional.isEmpty()){
            throw new EntityNotFoundException("Cuenta no encontrada id: " + id);
        }
        return optional.get();
    }

    private Account getEntityByNumber(String number) {
        Optional<Account> optional = this.accountRepository.findByNumber(number);
        if(optional.isEmpty()){
            throw new EntityNotFoundException("Cuenta no encontrada número: " + number);
        }
        return optional.get();
    }

    private AccountDto convertEntityToDto(Account entity) {
        return Mapper.modelMapper().map(entity, AccountDto.class);
    }

    private Account convertDtoToEntity(AccountDto entity) {
        return Mapper.modelMapper().map(entity, Account.class);
    }

}
