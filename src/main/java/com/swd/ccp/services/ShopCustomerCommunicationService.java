package com.swd.ccp.services;

import com.swd.ccp.DTO.request_models.ShopCustomerCommunicationRequest;
import com.swd.ccp.DTO.response_models.ShopCustomerCommunicationResponse;
import com.swd.ccp.models.entity_models.ShopCustomerCommunication;

import java.util.List;

public interface ShopCustomerCommunicationService {
    ShopCustomerCommunication createCommunication(ShopCustomerCommunicationRequest request);
    List<ShopCustomerCommunicationResponse> getAllCommunications();
}
