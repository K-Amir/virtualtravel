package com.virtualtravel.empresa.Booking.Domain;


import com.virtualtravel.empresa.Booking.Application.Dto.BookingFormInputDto;
import com.virtualtravel.empresa.Booking.Application.Dto.BookingOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingMapper {
    BookingMapper MAP = Mappers.getMapper(BookingMapper.class);

    BookingEntity bookingInDtoToEntity(BookingFormInputDto bookingFormInputDto);

    BookingOutputDto bookingEntityToOutput(BookingEntity bookingEntity);
}
