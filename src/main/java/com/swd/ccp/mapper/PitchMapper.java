package com.swd.ccp.mapper;

import com.swd.ccp.DTO.response_models.PitchResponse;
import com.swd.ccp.DTO.response_models.ShopResponse;
import com.swd.ccp.models.entity_models.Pitch;
import com.swd.ccp.models.entity_models.Shop;

public class PitchMapper {
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String ONGOING_STATUS = "Ongoing";
    private static final String FINISHED_STATUS = "Finished";
    public static PitchResponse pitchToDTO(Pitch c){

        /*LocalDate localDate = c.getCreateDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        String formattedString = localDate.format(formatter);*/

        return PitchResponse.builder()
                .name(c.getName())
//                .status(c.getStatus() ? ONGOING_STATUS : FINISHED_STATUS) them status
                .id(c.getId())
                .capacity(c.getCapacity())
                .pitchStatus(c.getPitchStatus())
                .name(c.getName())
                .shopName(c.getShop().getName())
                .build();
    }
}
