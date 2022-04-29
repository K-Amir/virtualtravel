package com.virtualtravel.empresa.Bus.Domain;


import com.virtualtravel.empresa.Bus.Application.Dto.BusInputDto;
import com.virtualtravel.empresa.Bus.Application.Dto.BusOutputDto;
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
}
