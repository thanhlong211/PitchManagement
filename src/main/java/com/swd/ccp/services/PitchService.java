package com.swd.ccp.services;

import com.swd.ccp.DTO.request_models.CreatePitchRequest;
import com.swd.ccp.DTO.request_models.UpdatePitchRequest;
import com.swd.ccp.DTO.response_models.PitchResponse;
import com.swd.ccp.DTO.response_models.ResponseObject;

import java.util.List;

public interface PitchService {
    ResponseObject createPitch(CreatePitchRequest createPitchRequest);
    ResponseObject updatePitch(UpdatePitchRequest updatePitchRequest);
    ResponseObject changePitchStatus(Integer id, String status);
    List<PitchResponse> getAllPitches(String name);
    public List<PitchResponse> getActivePitches(String name);
}
