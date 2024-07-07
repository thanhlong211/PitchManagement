package com.swd.ccp.services_implementors;

import com.swd.ccp.DTO.request_models.CreatePitchRequest;
import com.swd.ccp.DTO.request_models.UpdatePitchRequest;
import com.swd.ccp.DTO.response_models.PitchResponse;
import com.swd.ccp.DTO.response_models.ResponseObject;
import com.swd.ccp.mapper.PitchMapper;
import com.swd.ccp.models.entity_models.Booking;
import com.swd.ccp.models.entity_models.Pitch;
import com.swd.ccp.models.entity_models.Shop;
import com.swd.ccp.repositories.BookingRepo;
import com.swd.ccp.repositories.PitchRepo;
import com.swd.ccp.repositories.ShopRepo;
import com.swd.ccp.services.PitchService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PitchServiceImpl implements PitchService {

    private final PitchRepo pitchRepo;
    private final BookingRepo bookingRepo;
    private final ShopRepo shopRepo;

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
    public List<PitchResponse> getAllPitchesByShopId(Integer shopId,String name) {
        List<Pitch> pitches;
        Optional<Shop> shopOptional = shopRepo.findById(shopId);

        if (!shopOptional.isPresent()) {
            throw new EntityNotFoundException("Shop not found with id: " + shopId);
        }
        Shop shop = shopOptional.get();
        if (name != null && !name.isBlank()) {
            pitches = pitchRepo.findAllByShopAndNameContainingIgnoreCase(shop,name);
        } else {
            pitches = pitchRepo.findAllByShop(shop);
        }

        return pitches.stream()
                .map(PitchMapper::pitchToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PitchResponse> getActivePitches(Integer shopId, String name) {
        List<Pitch> activePitches;
        Optional<Shop> shopOptional = shopRepo.findById(shopId);

        if (!shopOptional.isPresent()) {
            throw new EntityNotFoundException("Shop not found with id: " + shopId);
        }

        Shop shop = shopOptional.get();

        if (name != null && !name.isEmpty()) {
            activePitches = pitchRepo.findAllByShopAndNameContainingIgnoreCaseAndPitchStatus(shop, name, "ACTIVE");
        } else {
            activePitches = pitchRepo.findAllByShopAndPitchStatus(shop, "ACTIVE");
        }

        return activePitches.stream()
                .map(PitchMapper::pitchToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PitchResponse> getAvailablePitches(Integer shopId, LocalDate bookingDate, LocalTime startBooking, LocalTime endBooking) {
        // Get all pitches for the specific shopId
        Optional<Shop> shopOptional = shopRepo.findById(shopId);

        if (!shopOptional.isPresent()) {
            throw new EntityNotFoundException("Shop not found with id: " + shopId);
        }

        Shop shop = shopOptional.get();
        List<Pitch> allPitches = pitchRepo.findAllByShop(shop);

        // Get all bookings for the specified date and time range
        List<Booking> conflictingBookings = bookingRepo.findByBookingDateAndStartBookingLessThanEqualAndEndBookingGreaterThanEqual(
                bookingDate,
                endBooking,
                startBooking
        );

        // Filter out pitches that are booked
        List<Pitch> availablePitches = allPitches.stream()
                .filter(pitch -> conflictingBookings.stream()
                        .noneMatch(booking -> booking.getPitch().equals(pitch)))
                .collect(Collectors.toList());

        // Map the available pitches to PitchResponse DTOs
        return availablePitches.stream()
                .map(PitchMapper::pitchToDTO)
                .collect(Collectors.toList());
    }

}