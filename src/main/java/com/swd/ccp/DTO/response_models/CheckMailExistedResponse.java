package com.swd.ccp.DTO.response_models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckMailExistedResponse {

    private String message;
    private boolean status;
}
