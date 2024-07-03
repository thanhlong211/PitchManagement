package com.swd.ccp.mapper;

import com.swd.ccp.DTO.response_models.ShopResponse;
import com.swd.ccp.models.entity_models.Shop;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ShopMapper {
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String ONGOING_STATUS = "Ongoing";
    private static final String FINISHED_STATUS = "Finished";
    public static ShopResponse shopToDTO(Shop c){

        /*LocalDate localDate = c.getCreateDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        String formattedString = localDate.format(formatter);*/


        return ShopResponse.builder()
                .name(c.getName())
//                .status(c.getStatus() ? ONGOING_STATUS : FINISHED_STATUS) them status
                .id(c.getId())
                .address(c.getAddress())
                .avatar(c.getAvatar())
                .openTime(c.getOpenTime())
                .closeTime(c.getCloseTime())
                .status(c.getStatus())
                .build();
    }
}
