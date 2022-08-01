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
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class MovementServiceImplTest {

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
        Movement movementEntity = Movement
                .builder()
                .id(id)
                .type(Movement.Type.DEBIT)
                .build();

        Mockito.when(this.movementRepositoryMock.findById(id)).thenReturn(Optional.of(movementEntity));
        MovementDto dto = this.movementService.getById(id);

        assertNotNull(dto);
        assertEquals(1, dto.getId());
    }

    @Test
    void save() {
        MovementDto movementDto = MovementDto
                .builder()
                .id(1l)
                .type(Movement.Type.CREDIT)
                .accountId(10l)
                .amount(new BigDecimal(1000))
                .build();

        Movement movement = Movement
                .builder()
                .id(1l)
                .type(Movement.Type.CREDIT)
                .amount(new BigDecimal(1000))
                .build();

        Mockito.when(movementRepositoryMock.save(Mockito.any(Movement.class))).thenReturn(movement);
        MovementDto dto = this.movementService.create(movementDto);

        assertNotNull(dto);
        assertEquals(movement.getAmount(), dto.getAmount());
    }

}
