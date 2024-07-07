package com.swd.ccp.repositories;

import com.swd.ccp.models.entity_models.Pitch;
import com.swd.ccp.models.entity_models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PitchRepo extends JpaRepository<Pitch,Integer> {
    Optional<Pitch> findByName(String pitchName);

    List<Pitch> findByPitchStatus(String status);

    List<Pitch> findAllByShopAndNameContainingIgnoreCase(Shop shop,String name);



    List<Pitch> findAllByShopAndNameContainingIgnoreCaseAndPitchStatus(Shop shop, String name, String active);

    List<Pitch> findAllByShopAndPitchStatus(Shop shop, String active);

    List<Pitch> findAllByShop(Shop shop);
}
