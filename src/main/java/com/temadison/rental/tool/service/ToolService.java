package com.temadison.rental.tool.service;

import com.temadison.rental.tool.data.model.ToolMO;
import com.temadison.rental.tool.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolService {

    @Autowired
    private ToolRepository toolRepository;

    public List<ToolMO> getAllTools() {
        return toolRepository.findAll();
    }

    public ToolMO getToolById(Long id) {
        return toolRepository.findById(id).orElse(null);
    }

    public ToolMO getToolByCode(String code) {
        return toolRepository.findFirstByCode(code).orElse(null);
    }
}