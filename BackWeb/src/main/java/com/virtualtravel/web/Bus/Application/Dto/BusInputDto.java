package com.virtualtravel.web.Bus.Application.Dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BusInputDto {
    private Integer id;

    private String hour;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;

    private String city;

    private Integer availableSeats;

}
