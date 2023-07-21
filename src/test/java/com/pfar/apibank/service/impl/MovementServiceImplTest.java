package com.pfar.apibank.service.impl;

import com.pfar.apibank.domain.Movement;
import com.pfar.apibank.domain.dto.MovementDto;
import com.pfar.apibank.repository.MovementRepository;
import com.pfar.apibank.service.MovementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class MovementServiceImplTest {

    private MovementService movementService;
    private MovementRepository movementRepositoryMock;

    @BeforeEach
    void setUp() {
        this.movementRepositoryMock = Mockito.mock(MovementRepository.class);
        this.movementService = new MovementServiceImpl(this.movementRepositoryMock);
    }

    @Test
    void getById() {
        long id = 1;
        Movement movementEntity = new Movement();
        movementEntity.setId(id);
        movementEntity.setType(Movement.Type.DEBIT);

        Mockito.when(this.movementRepositoryMock.findById(id)).thenReturn(Optional.of(movementEntity));
        MovementDto dto = this.movementService.getById(id);

        assertNotNull(dto);
        assertEquals(1, dto.getId());
    }

}
