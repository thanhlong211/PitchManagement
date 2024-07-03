package com.swd.ccp.repositories;

import com.swd.ccp.models.entity_models.Shop;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopRepo extends JpaRepository<Shop,Integer> {
    Optional<Shop> findAllByName(String name);
    List<Shop> findByStatus(String status);
    List<Shop> findAllByNameContainingIgnoreCaseAndStatus(String name, String status);

    List<Shop> findAllByNameContainingIgnoreCase(String name);
}
