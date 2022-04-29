package com.virtualtravel.empresa.Mail.Domain;

import com.virtualtravel.empresa.Booking.Domain.BookingEntity;
import com.virtualtravel.empresa.Mail.Application.Dto.MailOutputDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-29T13:18:50+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18 (Oracle Corporation)"
)
public class MailMapperImpl implements MailMapper {

    @Override
    public MailOutputDto bookingListToMailOutputDto(BookingEntity bookingEntityList) {
        if ( bookingEntityList == null ) {
            return null;
        }

        MailOutputDto mailOutputDto = new MailOutputDto();

        mailOutputDto.setDestinationCity( bookingEntityList.getCity() );
        mailOutputDto.setBookingDate( bookingEntityList.getDate() );
        mailOutputDto.setBookingHour( bookingEntityList.getHour() );
        mailOutputDto.setEmail( bookingEntityList.getEmail() );

        return mailOutputDto;
    }
}
