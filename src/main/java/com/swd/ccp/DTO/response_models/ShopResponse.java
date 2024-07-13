package com.swd.ccp.DTO.response_models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopResponse {
    private int id;
    private String address;
    private String avatar;
    private String openTime;
    private String closeTime;
    private String name;
    private String status;
    private String phone;
}
