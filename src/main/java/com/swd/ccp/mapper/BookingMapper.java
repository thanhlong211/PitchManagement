package com.swd.ccp.mapper;

import com.swd.ccp.DTO.response_models.BookingResponse;
import com.swd.ccp.DTO.response_models.PitchResponse;
import com.swd.ccp.models.entity_models.Booking;
import com.swd.ccp.models.entity_models.Pitch;

public class BookingMapper {
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String ONGOING_STATUS = "Ongoing";
    private static final String FINISHED_STATUS = "Finished";
    public static BookingResponse bookingToDTO(Booking c){

        /*LocalDate localDate = c.getCreateDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        String formattedString = localDate.format(formatter);*/

        return BookingResponse.builder()
                .customerName(c.getAccount().getName())
//                .status(c.getStatus() ? ONGOING_STATUS : FINISHED_STATUS) them status
                .id(c.getId())
                .bookingDate(c.getBookingDate())
                .bookingStatus(c.getBookingStatus())
                .endBooking(c.getEndBooking())
                .price(c.getPrice())
                .capacity(c.getPitch().getCapacity())
                .pitchName(c.getPitch().getName())
                .startBooking(c.getStartBooking())
                .createDate(c.getCreateDate())
                .shopName(c.getPitch().getShop().getName())
                .build();
    }
}
