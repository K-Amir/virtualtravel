package com.virtualtravel.web.Bus.Domain;


import com.virtualtravel.web.Bookings.Application.Dto.AvailableBookingOutputDto;
import com.virtualtravel.web.Bus.Application.Dto.BusInputDto;
import com.virtualtravel.web.Bus.Application.Dto.BusOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BusMapper {
    BusMapper MAP = Mappers.getMapper(BusMapper.class);

    BusEntity inputDtoToEntity(BusInputDto busInputDto);

    List<BusOutputDto> inputListToOutput(List<BusEntity> busEntityList);

    BusOutputDto entityToOutput(BusEntity busEntity);

    @Mapping(source = "hour", target = "departmentHour")
    @Mapping(source = "date", target = "departmentDate")
    @Mapping(source = "city", target = "destinationCity")
    @Mapping(source = "availableSeats", target = "seatsAvailable")
    AvailableBookingOutputDto inputListToAvailableList(BusEntity busEntityList);
}
