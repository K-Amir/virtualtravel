package com.virtualtravel.empresa.Booking.Domain;

import com.virtualtravel.empresa.Booking.Application.Dto.BookingFormInputDto;
import com.virtualtravel.empresa.Booking.Application.Dto.BookingOutputDto;
import java.text.SimpleDateFormat;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-02T19:15:02+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18 (Oracle Corporation)"
)
public class BookingMapperImpl implements BookingMapper {

    @Override
    public BookingEntity bookingInDtoToEntity(BookingFormInputDto bookingFormInputDto) {
        if ( bookingFormInputDto == null ) {
            return null;
        }

        BookingEntity bookingEntity = new BookingEntity();

        bookingEntity.setName( bookingFormInputDto.getName() );
        bookingEntity.setPhone( bookingFormInputDto.getPhone() );
        bookingEntity.setEmail( bookingFormInputDto.getEmail() );
        bookingEntity.setDate( bookingFormInputDto.getDate() );
        bookingEntity.setHour( bookingFormInputDto.getHour() );
        bookingEntity.setCity( bookingFormInputDto.getCity() );

        return bookingEntity;
    }

    @Override
    public BookingOutputDto bookingEntityToOutput(BookingEntity bookingEntity) {
        if ( bookingEntity == null ) {
            return null;
        }

        BookingOutputDto bookingOutputDto = new BookingOutputDto();

        bookingOutputDto.setCity( bookingEntity.getCity() );
        bookingOutputDto.setName( bookingEntity.getName() );
        bookingOutputDto.setPhone( bookingEntity.getPhone() );
        bookingOutputDto.setEmail( bookingEntity.getEmail() );
        bookingOutputDto.setHour( bookingEntity.getHour() );
        if ( bookingEntity.getDate() != null ) {
            bookingOutputDto.setDate( new SimpleDateFormat().format( bookingEntity.getDate() ) );
        }

        return bookingOutputDto;
    }
}
