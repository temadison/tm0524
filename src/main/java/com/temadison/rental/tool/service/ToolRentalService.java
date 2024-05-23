package com.temadison.rental.tool.service;

import com.temadison.rental.tool.model.AgreementDTO;
import com.temadison.rental.tool.model.CheckoutDTO;
import com.temadison.rental.tool.model.ToolMO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToolRentalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToolRentalService.class);

    @Autowired
    private ToolService toolService;

    public AgreementDTO checkout(CheckoutDTO checkoutDTO) {
        LOGGER.info("Performing checkout from DTO: " + checkoutDTO);

        ToolMO toolMO = toolService.getToolByCode(checkoutDTO.getToolCode());
        AgreementDTO agreementDTO = new AgreementDTO(toolMO, checkoutDTO.getNumberOfDays(), checkoutDTO.getDiscountPercent(), checkoutDTO.getCheckoutDate());
        System.out.println(agreementDTO.asReport());

        return agreementDTO;
    }
}
