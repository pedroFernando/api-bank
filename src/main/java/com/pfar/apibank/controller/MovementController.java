package com.pfar.apibank.controller;

import com.pfar.apibank.domain.dto.MovementDto;
import com.pfar.apibank.service.MovementService;
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
@RequestMapping(value = "/movimientos", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovementController {
    private final MovementService movementService;

    @GetMapping
    public ResponseEntity<List<MovementDto>> getAll() {
        log.info("Get all movements");
        return ResponseEntity.ok(this.movementService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovementDto> getById(@PathVariable long id) {
        log.info("Get movement by id = " + id);
        return ResponseEntity.ok(this.movementService.getById(id));
    }

    @GetMapping("/cuenta/{idAccount}")
    public ResponseEntity<List<MovementDto>> getByIdClient(@PathVariable long idAccount) {
        log.info("Get movements by account");
        return ResponseEntity.ok(this.movementService.getByAccount(idAccount));
    }

    @PostMapping
    public ResponseEntity<MovementDto> create(@RequestBody @Valid MovementDto data) {
        log.info("Create movement = " + data);
        return new ResponseEntity<>(this.movementService.create(data), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable long id) {
        log.info("Delete movement by id = " + id);
        return ResponseEntity.ok(this.movementService.deleteById(id));
    }
}
