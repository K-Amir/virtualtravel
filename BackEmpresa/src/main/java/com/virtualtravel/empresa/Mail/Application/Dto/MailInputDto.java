package com.virtualtravel.empresa.Mail.Application.Dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MailInputDto {
    private String ciudadDestino;

    private String email;

    private String bookingHour;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date bookingDate;
}
