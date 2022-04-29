package com.virtualtravel.empresa.Mail.Domain;

import com.virtualtravel.empresa.Booking.Domain.BookingEntity;
import com.virtualtravel.empresa.Mail.Application.Dto.MailOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MailMapper {

    MailMapper MAP = Mappers.getMapper(MailMapper.class);


    @Mapping(source = "city", target = "destinationCity")
    @Mapping(source = "date", target = "bookingDate")
    @Mapping(source = "hour", target = "bookingHour")
    MailOutputDto bookingListToMailOutputDto(BookingEntity bookingEntityList);
}
