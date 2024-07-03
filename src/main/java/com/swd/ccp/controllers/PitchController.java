package com.swd.ccp.controllers;

import com.swd.ccp.DTO.request_models.CreatePitchRequest;
import com.swd.ccp.DTO.request_models.UpdatePitchRequest;
import com.swd.ccp.DTO.response_models.PitchResponse;
import com.swd.ccp.DTO.response_models.ResponseObject;
import com.swd.ccp.services.PitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pitches")
public class PitchController {

    private final PitchService pitchService;

    @Autowired
    public PitchController(PitchService pitchService) {
        this.pitchService = pitchService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('owner:create')")
    public ResponseObject createPitch(@RequestBody CreatePitchRequest createPitchRequest) {
        return pitchService.createPitch(createPitchRequest);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('owner:update')")
    public ResponseObject updatePitch(@RequestBody UpdatePitchRequest updatePitchRequest) {
        return pitchService.updatePitch(updatePitchRequest);
    }

    @PostMapping("/changeStatus/{id}/{status}")
    @PreAuthorize("hasAuthority('owner:update')")
    public ResponseObject changePitchStatus(@PathVariable Integer id, @PathVariable String status) {
        return pitchService.changePitchStatus(id, status);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('owner:read')")
    public ResponseEntity<List<PitchResponse>> getAllPitches(
            @RequestParam(defaultValue = "", name = "search") String search) {
        List<PitchResponse> pitches = pitchService.getAllPitches(search);
        return ResponseEntity.ok(pitches);
    }

    @GetMapping("/active")
    @PreAuthorize("hasAuthority('customer:read')")
    public ResponseEntity<List<PitchResponse>> getActivePitches(
            @RequestParam(defaultValue = "", name = "search") String search) {
        List<PitchResponse> activePitches = pitchService.getActivePitches(search);
        return ResponseEntity.ok(activePitches);
    }
}