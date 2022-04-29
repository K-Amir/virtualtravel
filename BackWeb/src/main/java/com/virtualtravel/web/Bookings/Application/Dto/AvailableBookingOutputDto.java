package com.virtualtravel.web.Bookings.Application.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AvailableBookingOutputDto {
    private String destinationCity;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date departmentDate;

    private String departmentHour;

    private Integer seatsAvailable;
}
