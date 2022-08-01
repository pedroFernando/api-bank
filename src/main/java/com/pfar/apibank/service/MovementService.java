package com.pfar.apibank.service;

import com.pfar.apibank.domain.dto.MovementDto;

import java.util.List;

/**
 * CRUD movement
 *
 * @author Pedro Aponte <fernandoaponte1103@gmail.com>
 * @version 0.0.1
 */
public interface MovementService {
    /**
     * Get movement by id
     * @param id ID movement to search
     * @return Movement
     */
    MovementDto getById(long id);

    /**
     * Get all movements
     *
     * @return List of movements
     */
    List<MovementDto> getAll();

    /**
     * Get all account movements
     * @param idAccount ID account
     * @return List of client accounts
     */
    List<MovementDto> getByAccount(long idAccount);

    /**
     * Create movement
     * @param data Object with information of movement
     * @return Movement
     */
    MovementDto create(MovementDto data);

    /**
     * Delete movement
     * @param id ID movement
     * @return ID
     */
    long deleteById(long id);
}
