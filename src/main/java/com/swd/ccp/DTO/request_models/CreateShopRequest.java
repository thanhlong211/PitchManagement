package com.swd.ccp.DTO.request_models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateShopRequest {
    private String address;
    private String avatar;
    private String openTime;
    private String closeTime;
    private String name;
    private String phone;
}
