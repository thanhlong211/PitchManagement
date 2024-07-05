package com.swd.ccp.controllers;

import com.swd.ccp.DTO.request_models.ShopCustomerCommunicationRequest;
import com.swd.ccp.DTO.response_models.ShopCustomerCommunicationResponse;
import com.swd.ccp.models.entity_models.ShopCustomerCommunication;
import com.swd.ccp.services.ShopCustomerCommunicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/communications")
public class ShopCustomerCommunicationController {

    @Autowired
    private ShopCustomerCommunicationService communicationService;

    @PostMapping
    public ResponseEntity<ShopCustomerCommunication> createCommunication(@RequestBody ShopCustomerCommunicationRequest request) {
        ShopCustomerCommunication communication = communicationService.createCommunication(request);
        return ResponseEntity.ok(communication);
    }

    @GetMapping
    public ResponseEntity<List<ShopCustomerCommunicationResponse>> getAllCommunications() {
        List<ShopCustomerCommunicationResponse> responses = communicationService.getAllCommunications();
        return ResponseEntity.ok(responses);
    }
}
