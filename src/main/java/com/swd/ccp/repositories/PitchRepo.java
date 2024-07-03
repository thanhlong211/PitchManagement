package com.swd.ccp.repositories;

import com.swd.ccp.models.entity_models.Pitch;
import com.swd.ccp.models.entity_models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PitchRepo extends JpaRepository<Pitch,Integer> {
    Optional<Pitch> findByName(String pitchName);

    List<Pitch> findByPitchStatus(String status);

    List<Pitch> findAllByNameContainingIgnoreCase(String name);

    List<Pitch> findAllByNameContainingIgnoreCaseAndPitchStatus(String name, String active);
}
