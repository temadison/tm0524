package com.temadison.rental.tool.repository;

import com.temadison.rental.tool.data.model.ToolMO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToolRepository extends JpaRepository<ToolMO, Long>, CustomToolRepository {

}
