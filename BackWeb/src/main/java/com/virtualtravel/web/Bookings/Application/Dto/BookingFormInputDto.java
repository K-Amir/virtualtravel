package com.virtualtravel.web.Bookings.Application.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingFormInputDto  {
    @NotEmpty(message = "City must be filled, Ex - 'Madrid'")
    private String city;

    @NotEmpty(message = "Name  must be filled, Ex - 'Jhon'")
    private String name;

    @NotEmpty(message = "Phone  must be filled, Ex - '0000000000'")
    private String phone;

    @NotEmpty(message = "Email must be filled, Ex - 'jhon@doe.boot'")
    private String email;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "You must provide a valid date value, Ex '03-05-2022'")
    private Date date;

    @NotEmpty(message = "Hour must be filled, Ex - '16:30'")
    @Pattern(regexp = "([0-1][0-9]|2[0-3]):[0-5][0-9]", message = "Hour does not follow a valid pattern, Ex - '16:30'")
    private String hour;
}
