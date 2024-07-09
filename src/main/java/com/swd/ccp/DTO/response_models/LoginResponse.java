package com.swd.ccp.DTO.response_models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {

    private String message;
    private Integer shopId;

    private boolean status;

    private Integer account_id;
}
