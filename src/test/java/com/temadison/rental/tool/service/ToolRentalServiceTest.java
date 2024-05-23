package com.temadison.rental.tool.service;

import com.temadison.rental.tool.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class ToolRentalServiceTest {

    private static final String TOOL_CODE_CHNS = "CHNS";
    private static final ToolType TOOL_TYPE_CHNS = ToolType.CHAINSAW;
    private static final Brand TOOL_BRAND_CHNS = Brand.STIHL;
    private static final BigDecimal TOOL_DAILY_RATE_CHNS = new BigDecimal("1.49");

    private static final String TOOL_CODE_JAKD = "JAKD";
    private static final ToolType TOOL_TYPE_JAKD = ToolType.JACKHAMMER;
    private static final Brand TOOL_BRAND_JAKD = Brand.DEWALT;
    private static final BigDecimal TOOL_DAILY_RATE_JAKD = new BigDecimal("2.99");

    private static final String TOOL_CODE_JAKR = "JAKR";
    private static final ToolType TOOL_TYPE_JAKR = ToolType.JACKHAMMER;
    private static final Brand TOOL_BRAND_JAKR = Brand.RIDGID;
    private static final BigDecimal TOOL_DAILY_RATE_JAKR = new BigDecimal("2.99");

    private static final String TOOL_CODE_LADW = "LADW";
    private static final ToolType TOOL_TYPE_LADW = ToolType.LADDER;
    private static final Brand TOOL_BRAND_LADW = Brand.WERNER;
    private static final BigDecimal TOOL_DAILY_RATE_LADW = new BigDecimal("1.99");

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
        ToolMO toolMO = new ToolMO(TOOL_CODE_JAKR, TOOL_TYPE_JAKR, TOOL_BRAND_JAKR, TOOL_DAILY_RATE_JAKR);

        when(toolService.getToolByCode(TOOL_CODE_JAKR)).thenReturn(toolMO);

        CheckoutDTO checkoutDTO = new CheckoutDTO(toolMO.getCode(), 5, 101, LocalDate.of(2015, 9, 15));
        AgreementDTO agreementDTO = toolRentalService.checkout(checkoutDTO);

        assertNotNull(agreementDTO);
        assertEquals(checkoutDTO.getToolCode(), agreementDTO.getTool().getCode());
    }

    @Test
    void checkoutTest2() {
        ToolMO toolMO = new ToolMO(TOOL_CODE_LADW, TOOL_TYPE_LADW, TOOL_BRAND_LADW, TOOL_DAILY_RATE_LADW);

        when(toolService.getToolByCode(TOOL_CODE_LADW)).thenReturn(toolMO);

        CheckoutDTO checkoutDTO = new CheckoutDTO(toolMO.getCode(), 3, 10, LocalDate.of(2020, 7, 2));
        AgreementDTO agreementDTO = toolRentalService.checkout(checkoutDTO);

        assertNotNull(agreementDTO);
        assertEquals(checkoutDTO.getToolCode(), agreementDTO.getTool().getCode());
    }

    @Test
    void checkoutTest3() {
        ToolMO toolMO = new ToolMO(TOOL_CODE_CHNS, TOOL_TYPE_CHNS, TOOL_BRAND_CHNS, TOOL_DAILY_RATE_CHNS);

        when(toolService.getToolByCode(TOOL_CODE_CHNS)).thenReturn(toolMO);

        CheckoutDTO checkoutDTO = new CheckoutDTO(toolMO.getCode(), 5, 25, LocalDate.of(2015, 7, 2));
        AgreementDTO agreementDTO = toolRentalService.checkout(checkoutDTO);

        assertNotNull(agreementDTO);
        assertEquals(checkoutDTO.getToolCode(), agreementDTO.getTool().getCode());
    }

    @Test
    void checkoutTest4() {
        ToolMO toolMO = new ToolMO(TOOL_CODE_JAKD, TOOL_TYPE_JAKD, TOOL_BRAND_JAKD, TOOL_DAILY_RATE_JAKD);

        when(toolService.getToolByCode(TOOL_CODE_JAKD)).thenReturn(toolMO);

        CheckoutDTO checkoutDTO = new CheckoutDTO(toolMO.getCode(), 6, 0, LocalDate.of(2015, 9, 3));
        AgreementDTO agreementDTO = toolRentalService.checkout(checkoutDTO);

        assertNotNull(agreementDTO);
        assertEquals(checkoutDTO.getToolCode(), agreementDTO.getTool().getCode());
    }

    @Test
    void checkoutTest5() {
        ToolMO toolMO = new ToolMO(TOOL_CODE_JAKR, TOOL_TYPE_JAKR, TOOL_BRAND_JAKR, TOOL_DAILY_RATE_JAKR);

        when(toolService.getToolByCode(TOOL_CODE_JAKR)).thenReturn(toolMO);

        CheckoutDTO checkoutDTO = new CheckoutDTO(toolMO.getCode(), 9, 0, LocalDate.of(2015, 7, 2));
        AgreementDTO agreementDTO = toolRentalService.checkout(checkoutDTO);

        assertNotNull(agreementDTO);
        assertEquals(checkoutDTO.getToolCode(), agreementDTO.getTool().getCode());
    }

    @Test
    void checkoutTest6() {
        ToolMO toolMO = new ToolMO(TOOL_CODE_JAKR, TOOL_TYPE_JAKR, TOOL_BRAND_JAKR, TOOL_DAILY_RATE_JAKR);

        when(toolService.getToolByCode(TOOL_CODE_JAKR)).thenReturn(toolMO);

        CheckoutDTO checkoutDTO = new CheckoutDTO(toolMO.getCode(), 4, 50, LocalDate.of(2020, 7, 2));
        AgreementDTO agreementDTO = toolRentalService.checkout(checkoutDTO);

        assertNotNull(agreementDTO);
        assertEquals(checkoutDTO.getToolCode(), agreementDTO.getTool().getCode());
    }
}