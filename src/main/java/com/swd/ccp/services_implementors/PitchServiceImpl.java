package com.swd.ccp.services_implementors;

import com.swd.ccp.DTO.request_models.CreatePitchRequest;
import com.swd.ccp.DTO.request_models.UpdatePitchRequest;
import com.swd.ccp.DTO.response_models.PitchResponse;
import com.swd.ccp.DTO.response_models.ResponseObject;
import com.swd.ccp.mapper.PitchMapper;
import com.swd.ccp.models.entity_models.Pitch;
import com.swd.ccp.repositories.PitchRepo;
import com.swd.ccp.services.PitchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PitchServiceImpl implements PitchService {

    private final PitchRepo pitchRepo;

    @Override
    public ResponseObject createPitch(CreatePitchRequest createPitchRequest) {
        // Validate if pitch name already exists
        if (pitchRepo.findByName(createPitchRequest.getName()).isPresent()) {
            return ResponseObject.builder()
                    .message("Pitch name already exists")
                    .statusCode(400)
                    .build();
        }

        // Validate if price is positive
        if (createPitchRequest.getPrice() <= 0) {
            return ResponseObject.builder()
                    .message("Price must be a positive number")
                    .statusCode(400)
                    .build();
        }

        Pitch pitch = pitchRepo.save(
                Pitch.builder()
                        .name(createPitchRequest.getName())
                        .price(createPitchRequest.getPrice())
                        .capacity(createPitchRequest.getCapacity())
                        .pitchStatus("ACTIVE")
                        .build()
        );

        return ResponseObject.builder()
                .message("Create Pitch Success")
                .statusCode(200)
                .build();
    }

    @Override
    public ResponseObject updatePitch(UpdatePitchRequest updatePitchRequest) {
        // Find the pitch by ID
        Optional<Pitch> existingPitchOptional = pitchRepo.findById(updatePitchRequest.getId());
        if (!existingPitchOptional.isPresent()) {
            return ResponseObject.builder()
                    .message("Pitch not found")
                    .statusCode(404)
                    .build();
        }

        // Validate if price is positive
        if (updatePitchRequest.getPrice() <= 0) {
            return ResponseObject.builder()
                    .message("Price must be a positive number")
                    .statusCode(400)
                    .build();
        }

        // Update existing pitch entity
        Pitch existingPitch = existingPitchOptional.get();
        existingPitch.setName(updatePitchRequest.getName());
        existingPitch.setCapacity(updatePitchRequest.getCapacity());
        existingPitch.setPrice(updatePitchRequest.getPrice());

        // Save the updated pitch entity
        pitchRepo.save(existingPitch);

        return ResponseObject.builder()
                .message("Update Pitch Success")
                .statusCode(200)
                .build();
    }
    @Override
    public ResponseObject changePitchStatus(Integer id, String status) {
        // Find the pitch by ID
        Optional<Pitch> existingPitchOptional = pitchRepo.findById(id);
        if (!existingPitchOptional.isPresent()) {
            return ResponseObject.builder()
                    .message("Pitch not found")
                    .statusCode(404)
                    .build();
        }

        Pitch existingPitch = existingPitchOptional.get();
        existingPitch.setPitchStatus(status);

        // Save the updated pitch entity
        pitchRepo.save(existingPitch);

        return ResponseObject.builder()
                .message("Change Pitch Status Success")
                .statusCode(200)
                .build();
    }

    @Override
    public List<PitchResponse> getAllPitches(String name) {
        List<Pitch> pitches;

        if (name != null && !name.isBlank()) {
            pitches = pitchRepo.findAllByNameContainingIgnoreCase(name);
        } else {
            pitches = pitchRepo.findAll();
        }

        return pitches.stream()
                .map(PitchMapper::pitchToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PitchResponse> getActivePitches(String name) {
        List<Pitch> activePitches;

        if (name != null && !name.isEmpty()) {
            activePitches = pitchRepo.findAllByNameContainingIgnoreCaseAndPitchStatus(name, "ACTIVE");
        } else {
            activePitches = pitchRepo.findByPitchStatus("ACTIVE");
        }

        return activePitches.stream()
                .map(PitchMapper::pitchToDTO)
                .collect(Collectors.toList());
    }

}