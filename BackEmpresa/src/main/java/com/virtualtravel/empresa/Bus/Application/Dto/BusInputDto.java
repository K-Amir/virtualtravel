package com.virtualtravel.empresa.Bus.Application.Dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
public class BusInputDto {
    @NotEmpty(message = "Hour must be filled, Ex - '16:30'")
    @Pattern(regexp = "([0-1][0-9]|2[0-3]):[0-5][0-9]", message = "Hour does not follow a valid pattern, Ex - '16:30'")
    private String hour;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "You must provide a valid date value, Ex '03-05-2022'")
    private Date date;

    @NotEmpty(message = "City must be filled, Ex - 'Madrid'")
    private String city;

    @NotNull(message = "You must provide a valid availableSeats value, Ex 40")
    @Min(value = 0, message = "Min value for availableSeats is 0")
    @Max(value = 200, message = "Max value for availableSeats is 200")
    private Integer availableSeats;

}
