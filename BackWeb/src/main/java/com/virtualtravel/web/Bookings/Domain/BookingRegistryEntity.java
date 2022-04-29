package com.virtualtravel.web.Bookings.Domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "booking_registry")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingRegistryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date createdAt;

    private String email;

    private String city;

    private String hour;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;

    private boolean isAccepted;

}
