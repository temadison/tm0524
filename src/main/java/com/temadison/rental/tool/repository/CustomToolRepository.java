package com.temadison.rental.tool.repository;

import com.temadison.rental.tool.data.model.ToolMO;

import java.util.Optional;

public interface CustomToolRepository {

    /**
     * Find the tool using the specified code.
     *
     * @param code
     * @return
     */
    Optional<ToolMO> findFirstByCode(String code);
}
