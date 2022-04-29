package com.virtualtravel.web.Bookings.Domain;

import com.virtualtravel.web.Bookings.Application.Dto.BookingFormInputDto;
import com.virtualtravel.web.Bookings.Application.Dto.BookingOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookingMapper {

    BookingMapper MAP = Mappers.getMapper(BookingMapper.class);


    BookingOutputDto bookingInToOutDto(BookingFormInputDto bookingFormInputDto);

    List<BookingOutputDto> bookingListToOutDto(List<BookingRegistryEntity> bookingRegistryEntityList);
}
