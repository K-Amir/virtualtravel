package com.virtualtravel.empresa.Booking.Application.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class BookingFormInputDto {
    private String city;

    private String name;

    private String phone;

    private String email;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;

    private String hour;
}
