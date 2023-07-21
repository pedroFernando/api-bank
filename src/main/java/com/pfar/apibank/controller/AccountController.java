package com.pfar.apibank.controller;

import com.pfar.apibank.domain.dto.AccountDto;
import com.pfar.apibank.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/cuentas", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAll() {
        log.info("Get all accounts");
        return ResponseEntity.ok(this.accountService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getById(@PathVariable long id) {
        log.info("Get by id account = " + id);
        return ResponseEntity.ok(this.accountService.getById(id));
    }

    @GetMapping("/cliente/{idClient}")
    public ResponseEntity<List<AccountDto>> getByIdClient(@PathVariable long idClient) {
        log.info("Get account by id client");
        return ResponseEntity.ok(this.accountService.getByClient(idClient));
    }

    @PostMapping
    public ResponseEntity<AccountDto> create(@RequestBody @Valid AccountDto data) {
        log.info("Create account = " + data);
        return new ResponseEntity<>(this.accountService.create(data), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> update(@PathVariable long id, @RequestBody @Valid AccountDto data) {
        log.info("Update account id = " + id + ", data = " + data);
        return new ResponseEntity<>(this.accountService.update(id, data), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable long id) {
        log.info("Delete account by id = " + id);
        return ResponseEntity.ok(this.accountService.deleteById(id));
    }
}
