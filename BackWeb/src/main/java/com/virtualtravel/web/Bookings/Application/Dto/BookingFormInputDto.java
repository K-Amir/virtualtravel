package com.virtualtravel.web.Bookings.Application.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Setter
@Getter
public class BookingFormInputDto  {
    private String city;

    private String name;

    private String phone;

    private String email;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;

    private String hour;
}
