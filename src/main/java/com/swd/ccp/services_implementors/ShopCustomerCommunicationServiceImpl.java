package com.swd.ccp.services_implementors;

import com.swd.ccp.DTO.request_models.ShopCustomerCommunicationRequest;
import com.swd.ccp.DTO.response_models.ShopCustomerCommunicationResponse;
import com.swd.ccp.mapper.ShopCustomerCommunicationMapper;
import com.swd.ccp.models.entity_models.Customer;
import com.swd.ccp.models.entity_models.Shop;
import com.swd.ccp.models.entity_models.ShopCustomerCommunication;
import com.swd.ccp.repositories.CustomerRepo;
import com.swd.ccp.repositories.ShopCustomerCommunicationRepo;
import com.swd.ccp.repositories.ShopRepo;
import com.swd.ccp.services.ShopCustomerCommunicationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopCustomerCommunicationServiceImpl implements ShopCustomerCommunicationService {
    private final ShopCustomerCommunicationRepo shopCustomerCommunicationRepo;
    private final ShopRepo shopRepo;
    private final CustomerRepo customerRepo;

    @Override
    public ShopCustomerCommunication createCommunication(ShopCustomerCommunicationRequest request) {
        // Find the Shop entity
        Optional<Shop> optionalShop = shopRepo.findById(request.getShopId());
        Shop shop = optionalShop.orElseThrow(() -> new EntityNotFoundException("Shop not found with id: " + request.getShopId()));

        // Find the Customer entity
        Optional<Customer> optionalCustomer = customerRepo.findById(request.getCustomerId());
        Customer customer = optionalCustomer.orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + request.getCustomerId()));

        // Create ShopCustomerCommunication entity
        ShopCustomerCommunication communication = new ShopCustomerCommunication();
        communication.setShop(shop);
        communication.setCustomer(customer);
        communication.setMessage(request.getMessage());
        communication.setTimestamp(new Date()); // Set current timestamp

        // Save and return the created entity
        return shopCustomerCommunicationRepo.save(communication);
    }
    @Override
    public List<ShopCustomerCommunicationResponse> getAllCommunicationsByStatus(String status) {
        List<ShopCustomerCommunication> communications = shopCustomerCommunicationRepo.findByCustomerAccountStatus(status);
        return communications.stream()
                .map(ShopCustomerCommunicationMapper::communicationToDTO)
                .collect(Collectors.toList());
    }
}
