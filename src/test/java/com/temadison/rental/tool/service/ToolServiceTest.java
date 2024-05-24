package com.temadison.rental.tool.service;

import com.temadison.rental.tool.data.Brand;
import com.temadison.rental.tool.data.ToolType;
import com.temadison.rental.tool.data.model.ToolMO;
import com.temadison.rental.tool.repository.ToolRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ToolServiceTest {

    private static final String TOOL_CODE_CHNS = "CHNS";
    private static final ToolType TOOL_TYPE_CHNS = ToolType.CHAINSAW;
    private static final Brand TOOL_BRAND_CHNS = Brand.DEWALT;

    private static final String TOOL_CODE_LADW = "LADW";
    private static final ToolType TOOL_TYPE_LADW = ToolType.LADDER;
    private static final Brand TOOL_BRAND_LADW = Brand.WERNER;

    @Mock
    private ToolRepository toolRepository;

    @InjectMocks
    private ToolService toolService;

    public ToolServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test ToolService.getAllTools() works")
    public void testGetAllTools() {
        ToolMO tool1 = new ToolMO(TOOL_CODE_CHNS, TOOL_TYPE_CHNS, TOOL_BRAND_CHNS);
        ToolMO tool2 = new ToolMO(TOOL_CODE_LADW, TOOL_TYPE_LADW, TOOL_BRAND_LADW);

        when(toolRepository.findAll()).thenReturn(Arrays.asList(tool1, tool2));

        List<ToolMO> tools = toolService.getAllTools();
        assertEquals(2, tools.size());
        assertEquals(TOOL_CODE_CHNS, tools.get(0).getCode());
        assertEquals(TOOL_CODE_LADW, tools.get(1).getCode());
    }

    @Test
    @DisplayName("Test ToolService.getToolById() works when ID is found")
    public void testGetToolById() {
        ToolMO tool = new ToolMO(TOOL_CODE_CHNS, TOOL_TYPE_CHNS, TOOL_BRAND_CHNS);

        when(toolRepository.findById(1L)).thenReturn(Optional.of(tool));

        ToolMO foundTool = toolService.getToolById(1L);
        assertNotNull(foundTool);
        assertEquals(TOOL_CODE_CHNS, foundTool.getCode());
    }

    @Test
    @DisplayName("Test ToolService.getToolById() returns null when ID not found")
    public void testGetToolByIdNotFound() {
        when(toolRepository.findById(1L)).thenReturn(Optional.empty());

        ToolMO foundTool = toolService.getToolById(1L);

        assertNull(foundTool);
    }

    @Test
    @DisplayName("Test ToolService.getToolByCode() works when code is found")
    public void testGetToolByCode() {
        ToolMO tool = new ToolMO(TOOL_CODE_CHNS, TOOL_TYPE_CHNS, TOOL_BRAND_CHNS);

        when(toolRepository.findFirstByCode(TOOL_CODE_CHNS)).thenReturn(Optional.of(tool));

        ToolMO foundTool = toolService.getToolByCode(TOOL_CODE_CHNS);
        assertNotNull(foundTool);
        assertEquals(TOOL_CODE_CHNS, foundTool.getCode());
    }

    @Test
    @DisplayName("Test ToolService.getToolByCod() returns null when code not found")
    public void testGetToolByCodeNotFound() {
        when(toolRepository.findFirstByCode("MISSING")).thenReturn(Optional.empty());

        ToolMO foundTool = toolService.getToolByCode("MISSING");

        assertNull(foundTool);
    }
}
