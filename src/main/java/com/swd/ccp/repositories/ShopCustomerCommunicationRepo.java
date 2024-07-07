package com.swd.ccp.repositories;

import com.swd.ccp.models.entity_models.ShopCustomerCommunication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopCustomerCommunicationRepo extends JpaRepository<ShopCustomerCommunication, Integer> {
    List<ShopCustomerCommunication> findByCustomerAccountStatus(String status);
}
