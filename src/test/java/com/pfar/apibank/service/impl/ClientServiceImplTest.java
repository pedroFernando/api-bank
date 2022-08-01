package com.pfar.apibank.service.impl;

import com.pfar.apibank.domain.Client;
import com.pfar.apibank.domain.dto.ClientDto;
import com.pfar.apibank.repository.ClientRepository;
import com.pfar.apibank.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {

    private ClientService clientService;

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

        Mockito.when(this.clientRepositoryMock.findById(id)).thenReturn(Optional.of(clientEntity));
        ClientDto dto = this.clientService.getById(id);

        assertNotNull(dto);
        assertEquals(1, dto.getId());
    }

    @Test
    void save() {
        ClientDto clientDto = ClientDto
                .builder()
                .id(1l)
                .name("Pedro")
                .build();

        Client client = Client
                .builder()
                .id(1l)
                .name("Pedro")
                .build();

        Mockito.when(clientRepositoryMock.save(Mockito.any(Client.class))).thenReturn(client);
        ClientDto dto = this.clientService.create(clientDto);

        assertNotNull(dto);
        assertEquals(client.getName(), dto.getName());
    }

}
