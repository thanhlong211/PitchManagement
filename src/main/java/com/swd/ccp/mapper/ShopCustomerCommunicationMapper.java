package com.swd.ccp.mapper;

import com.swd.ccp.DTO.response_models.ShopCustomerCommunicationResponse;
import com.swd.ccp.models.entity_models.ShopCustomerCommunication;

public class ShopCustomerCommunicationMapper {
    public static ShopCustomerCommunicationResponse communicationToDTO(ShopCustomerCommunication communication) {
        return ShopCustomerCommunicationResponse.builder()
                .id(communication.getId())
                .shopId(communication.getShop().getId())
                .shopName(communication.getShop().getName())
                .customerId(communication.getCustomer().getId())
                .customerName(communication.getCustomer().getAccount().getName())
                .message(communication.getMessage())
                .timestamp(communication.getTimestamp())
                .build();
    }
}
