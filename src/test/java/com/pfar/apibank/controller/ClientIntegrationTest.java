package com.pfar.apibank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pfar.apibank.ApiBankApplication;
import com.pfar.apibank.domain.Client;
import com.pfar.apibank.domain.dto.ClientDto;
import com.pfar.apibank.repository.ClientRepository;
import com.pfar.apibank.util.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(
        classes = ApiBankApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Slf4j
public class ClientIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    ObjectMapper objectMapper;

    public static final MediaType APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON;

    @Test
    void getById() throws Exception {
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/clientes/1000"));
        response.andExpect(status().isOk());
        response.andExpect(jsonPath("$.dni", is("1102325648")));
    }

    @Test
    void getAllClients() throws Exception {
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/clientes"));
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(4)));
    }

    @Test
    void testCreateClient() throws Exception {
        ClientDto clientDto = ClientDto.builder().id(1L).name("Pedro").gender("MALE").age(35).dni("1103983498").address("Loja").phone("0991419277").password("1234").state(true).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(clientDto)))
                .andExpect(status().isCreated());

        assertTrue(clientRepository.findByDni("1103983498").isPresent());
    }

    @Test
    void testDeleteClient() throws Exception {
        Client client = Client.builder().id(1L).name("Pedro").gender("MALE").age(35).dni("1103983498").address("Loja").phone("0991419277").password("1234").state(true).build();
        clientRepository.save(client);
        assertTrue(clientRepository.findById(1L).isPresent());

        mockMvc.perform(MockMvcRequestBuilders.delete("/clientes/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", is(1)));

        assertFalse(clientRepository.findById(1L).isPresent());
    }

    @Test
    void testDeleteClientError() throws Exception {
        assertFalse(clientRepository.findById(0L).isPresent());

        mockMvc.perform(MockMvcRequestBuilders.delete("/clientes/{id}", 0))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message", is("Cliente no encontrado id: " + 0)));
    }

    @Test
    void testUpdateClient() throws Exception {
        Client client = Client.builder().id(1L).name("Pedro").gender("MALE").age(35).dni("1103983498").address("Loja").phone("0991419277").password("1234").state(true).build();
        clientRepository.save(client);
        assertTrue(clientRepository.findById(1L).isPresent());

        ClientDto clientDto = convertEntityToDto(client);
        clientDto.setName("Fernando");

        mockMvc.perform(MockMvcRequestBuilders.put("/clientes/{id}", 1)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(clientDto)))
                .andExpect(status().isCreated());

        Optional<Client> optional = clientRepository.findByDni("1103983498");
        assertTrue(optional.isPresent());
        assertEquals("Fernando", optional.get().getName());
    }

    private ClientDto convertEntityToDto(Client entity) {
        return Mapper.modelMapper().map(entity, ClientDto.class);
    }

}
