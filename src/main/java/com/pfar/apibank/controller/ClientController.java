package com.pfar.apibank.controller;

import com.pfar.apibank.domain.dto.ClientDto;
import com.pfar.apibank.service.ClientService;
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
@RequestMapping(value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientDto>> getAll() {
        log.info("Get all clients");
        return ResponseEntity.ok(this.clientService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getById(@PathVariable long id) {
        log.info("Get client by id = " + id);
        return ResponseEntity.ok(this.clientService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ClientDto> create(@RequestBody @Valid ClientDto data) {
        log.info("Create client = " + data);
        return new ResponseEntity<>(this.clientService.create(data), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable long id, @RequestBody @Valid ClientDto data) {
        log.info("Update client id = " + id + ", data = " + data);
        return new ResponseEntity<>(this.clientService.update(id, data), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable long id) {
        log.info("Delete client by id = " + id);
        return ResponseEntity.ok(this.clientService.deleteById(id));
    }
}
