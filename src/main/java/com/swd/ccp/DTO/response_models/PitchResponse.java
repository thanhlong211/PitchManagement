package com.swd.ccp.DTO.response_models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PitchResponse {
    private Integer id;
    private String pitchStatus;

    private String name;

    private int capacity;

    private String shopName;
}
