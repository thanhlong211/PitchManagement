package com.swd.ccp.DTO.request_models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopCustomerCommunicationRequest {
    private Integer shopId;
    private Integer customerId;
    private String message;
}
