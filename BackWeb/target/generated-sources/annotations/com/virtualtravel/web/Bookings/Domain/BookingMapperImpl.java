package com.virtualtravel.web.Bookings.Domain;

import com.virtualtravel.web.Bookings.Application.Dto.BookingFormInputDto;
import com.virtualtravel.web.Bookings.Application.Dto.BookingOutputDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-29T17:17:27+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18 (Oracle Corporation)"
)
public class BookingMapperImpl implements BookingMapper {

    @Override
    public BookingOutputDto bookingInToOutDto(BookingFormInputDto bookingFormInputDto) {
        if ( bookingFormInputDto == null ) {
            return null;
        }

        BookingOutputDto bookingOutputDto = new BookingOutputDto();

        bookingOutputDto.setCity( bookingFormInputDto.getCity() );
        bookingOutputDto.setName( bookingFormInputDto.getName() );
        bookingOutputDto.setPhone( bookingFormInputDto.getPhone() );
        bookingOutputDto.setEmail( bookingFormInputDto.getEmail() );
        bookingOutputDto.setDate( bookingFormInputDto.getDate() );
        bookingOutputDto.setHour( bookingFormInputDto.getHour() );

        return bookingOutputDto;
    }

    @Override
    public List<BookingOutputDto> bookingListToOutDto(List<BookingRegistryEntity> bookingRegistryEntityList) {
        if ( bookingRegistryEntityList == null ) {
            return null;
        }

        List<BookingOutputDto> list = new ArrayList<BookingOutputDto>( bookingRegistryEntityList.size() );
        for ( BookingRegistryEntity bookingRegistryEntity : bookingRegistryEntityList ) {
            list.add( bookingRegistryEntityToBookingOutputDto( bookingRegistryEntity ) );
        }

        return list;
    }

    protected BookingOutputDto bookingRegistryEntityToBookingOutputDto(BookingRegistryEntity bookingRegistryEntity) {
        if ( bookingRegistryEntity == null ) {
            return null;
        }

        BookingOutputDto bookingOutputDto = new BookingOutputDto();

        bookingOutputDto.setCity( bookingRegistryEntity.getCity() );
        bookingOutputDto.setEmail( bookingRegistryEntity.getEmail() );
        bookingOutputDto.setDate( bookingRegistryEntity.getDate() );
        bookingOutputDto.setHour( bookingRegistryEntity.getHour() );

        return bookingOutputDto;
    }
}
