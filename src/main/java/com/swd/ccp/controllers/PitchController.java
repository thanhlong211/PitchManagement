package com.swd.ccp.controllers;

import com.swd.ccp.DTO.request_models.AvailablePitchRequest;
import com.swd.ccp.DTO.request_models.CreatePitchRequest;
import com.swd.ccp.DTO.request_models.UpdatePitchRequest;
import com.swd.ccp.DTO.response_models.PitchResponse;
import com.swd.ccp.DTO.response_models.ResponseObject;
import com.swd.ccp.services.PitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/pitches")
public class PitchController {

    private final PitchService pitchService;

    @Autowired
    public PitchController(PitchService pitchService) {
        this.pitchService = pitchService;
    }

    @PostMapping("/owner/create")
    public ResponseObject createPitch(@RequestBody CreatePitchRequest createPitchRequest) {
        return pitchService.createPitch(createPitchRequest);
    }

    @PostMapping("/owner/update")
    public ResponseObject updatePitch(@RequestBody UpdatePitchRequest updatePitchRequest) {
        return pitchService.updatePitch(updatePitchRequest);
    }

    @PostMapping("/owner/changeStatus/{pitch_id}/{status}")
    public ResponseObject changePitchStatus(@PathVariable Integer pitch_id, @PathVariable String status) {
        return pitchService.changePitchStatus(pitch_id, status);
    }

    @GetMapping("/owner/all")
    public ResponseEntity<List<PitchResponse>> getAllPitches(
            @RequestParam Integer shopId,
            @RequestParam(defaultValue = "", name = "search") String search) {
        List<PitchResponse> pitches = pitchService.getAllPitchesByShopId(shopId,search);
        return ResponseEntity.ok(pitches);
    }

    @GetMapping("/customer/get_active_pitches")
    public ResponseEntity<List<PitchResponse>> getActivePitches(
            @RequestParam Integer shopId,
            @RequestParam(defaultValue = "", name = "search") String search) {
        List<PitchResponse> activePitches = pitchService.getActivePitches(shopId, search);
        return ResponseEntity.ok(activePitches);
    }

    @PostMapping("/customer/booking/available-pitches")
    public ResponseEntity<List<PitchResponse>> getAvailablePitches(
            @RequestParam Integer shopId,
            @RequestBody AvailablePitchRequest request) {
        List<PitchResponse> availablePitches = pitchService.getAvailablePitches(shopId, request.getBookingDate(), request.getStartBooking(), request.getEndBooking());
        return new ResponseEntity<>(availablePitches, HttpStatus.OK);
    }
}