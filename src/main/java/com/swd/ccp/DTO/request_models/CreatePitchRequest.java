package com.swd.ccp.DTO.request_models;

import com.swd.ccp.models.entity_models.Booking;
import com.swd.ccp.models.entity_models.Shop;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePitchRequest {


    private String name;

    private int capacity;
    private float price;

    private Integer shopId;
}
