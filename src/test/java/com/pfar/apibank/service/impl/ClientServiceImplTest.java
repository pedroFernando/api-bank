package com.pfar.apibank.service.impl;

import com.pfar.apibank.domain.Client;
import com.pfar.apibank.domain.dto.ClientDto;
import com.pfar.apibank.repository.ClientRepository;
import com.pfar.apibank.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepositoryMock;

    @BeforeEach
    void setUp() {
        this.clientRepositoryMock = Mockito.mock(ClientRepository.class);
        this.clientService = new ClientServiceImpl(this.clientRepositoryMock);
    }

    @Test
    void getById() {
        long id = 1;
        Client clientEntity = new Client();
        clientEntity.setId(id);
        clientEntity.setName("Pedro");

        Mockito.when(this.clientRepositoryMock.findById(id)).thenReturn(Optional.of(clientEntity));
        ClientDto dto = this.clientService.getById(id);

        assertNotNull(dto);
        assertEquals(1, dto.getId());
    }

}
