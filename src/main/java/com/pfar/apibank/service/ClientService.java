package com.pfar.apibank.service;

import com.pfar.apibank.domain.dto.ClientDto;

import java.util.List;

/**
 * CRUD client
 *
 * @author Pedro Aponte <fernandoaponte1103@gmail.com>
 * @version 0.0.1
 */
public interface ClientService {
    /**
     * Get client by id
     * @param id ID client to search
     * @return Client
     */
    ClientDto getById(long id);

    /**
     * Get all clients
     *
     * @return List of clients
     */
    List<ClientDto> getAll();

    /**
     * Create client
     * @param data Object with information of client
     * @return Client
     */
    ClientDto create(ClientDto data);

    /**
     * Update client
     * @param id ID client
     * @param data Object with information of client
     * @return Client
     */
    ClientDto update(long id, ClientDto data);

    /**
     * Delete client
     * @param id ID client
     * @return ID
     */
    long deleteById(long id);
}
