package com.swd.ccp.DTO.response_models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopCustomerCommunicationResponse {
    private Integer id;
    private Integer shopId;
    private String shopName;
    private Integer customerId;
    private String customerName;
    private String message;
    private Date timestamp;
}
