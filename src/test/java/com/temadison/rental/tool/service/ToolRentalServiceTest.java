package com.temadison.rental.tool.service;

import com.temadison.rental.tool.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class ToolRentalServiceTest {

    private static final String TOOL_CODE_CHNS = "CHNS";
    private static final ToolType TOOL_TYPE_CHNS = ToolType.CHAINSAW;
    private static final Brand TOOL_BRAND_CHNS = Brand.DEWALT;
    private static final Double TOOL_DAILY_RATE_CHNS = 1.49;

    private static final String TOOL_CODE_JAKD = "JAKD";
    private static final ToolType TOOL_TYPE_JAKD = ToolType.CHAINSAW;
    private static final Brand TOOL_BRAND_JAKD = Brand.DEWALT;
    private static final Double TOOL_DAILY_RATE_JAKD = 1.49;

    private static final String TOOL_CODE_JAKR = "JAKR";
    private static final ToolType TOOL_TYPE_JAKR = ToolType.CHAINSAW;
    private static final Brand TOOL_BRAND_JAKR = Brand.DEWALT;
    private static final Double TOOL_DAILY_RATE_JAKR = 1.49;

    private static final String TOOL_CODE_LADW = "LADW";
    private static final ToolType TOOL_TYPE_LADW = ToolType.CHAINSAW;
    private static final Brand TOOL_BRAND_LADW = Brand.DEWALT;
    private static final Double TOOL_DAILY_RATE_LADW = 1.49;

    @Mock
    private ToolService toolService;

    @InjectMocks
    private ToolRentalService toolRentalService;

    @BeforeEach
    void setUp() {
    }

    public ToolRentalServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void checkoutTest1() {
        ToolMO toolMO = new ToolMO(TOOL_CODE_CHNS, TOOL_TYPE_CHNS, TOOL_BRAND_CHNS, TOOL_DAILY_RATE_CHNS);

        when(toolService.getToolByCode(TOOL_CODE_CHNS)).thenReturn(toolMO);

        CheckoutDTO checkoutDTO = new CheckoutDTO(toolMO.getCode(), 5, 101, LocalDate.of(2015, 9, 15));
        AgreementDTO agreementDTO = toolRentalService.checkout(checkoutDTO);

        assertNotNull(agreementDTO);
        assertEquals(checkoutDTO.getToolCode(), agreementDTO.getTool().getCode());
    }
}