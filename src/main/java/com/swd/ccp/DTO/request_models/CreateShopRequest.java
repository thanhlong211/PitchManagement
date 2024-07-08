package com.swd.ccp.DTO.request_models;

import com.swd.ccp.models.entity_models.Manager;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateShopRequest {
    private Integer owner_id;
    private String address;
    private String avatar;
    private String openTime;
    private String closeTime;
    private String name;
    private String phone;
}
