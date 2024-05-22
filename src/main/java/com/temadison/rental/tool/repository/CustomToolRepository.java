package com.temadison.rental.tool.repository;

import com.temadison.rental.tool.model.ToolMO;

import java.util.Optional;

public interface CustomToolRepository {

    Optional<ToolMO> findFirstByCode(String code);
}
