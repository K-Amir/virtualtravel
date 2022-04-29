package com.virtualtravel.web.Bus.Domain;

import com.virtualtravel.web.Bookings.Application.Dto.AvailableBookingOutputDto;
import com.virtualtravel.web.Bus.Application.Dto.BusInputDto;
import com.virtualtravel.web.Bus.Application.Dto.BusOutputDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-29T17:17:27+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18 (Oracle Corporation)"
)
public class BusMapperImpl implements BusMapper {

    @Override
    public BusEntity inputDtoToEntity(BusInputDto busInputDto) {
        if ( busInputDto == null ) {
            return null;
        }

        BusEntity busEntity = new BusEntity();

        busEntity.setId( busInputDto.getId() );
        busEntity.setHour( busInputDto.getHour() );
        busEntity.setDate( busInputDto.getDate() );
        busEntity.setCity( busInputDto.getCity() );
        busEntity.setAvailableSeats( busInputDto.getAvailableSeats() );

        return busEntity;
    }

    @Override
    public List<BusOutputDto> inputListToOutput(List<BusEntity> busEntityList) {
        if ( busEntityList == null ) {
            return null;
        }

        List<BusOutputDto> list = new ArrayList<BusOutputDto>( busEntityList.size() );
        for ( BusEntity busEntity : busEntityList ) {
            list.add( entityToOutput( busEntity ) );
        }

        return list;
    }

    @Override
    public BusOutputDto entityToOutput(BusEntity busEntity) {
        if ( busEntity == null ) {
            return null;
        }

        BusOutputDto busOutputDto = new BusOutputDto();

        busOutputDto.setId( busEntity.getId() );
        busOutputDto.setHour( busEntity.getHour() );
        busOutputDto.setDate( busEntity.getDate() );
        busOutputDto.setCity( busEntity.getCity() );
        busOutputDto.setAvailableSeats( busEntity.getAvailableSeats() );

        return busOutputDto;
    }

    @Override
    public AvailableBookingOutputDto inputListToAvailableList(BusEntity busEntityList) {
        if ( busEntityList == null ) {
            return null;
        }

        AvailableBookingOutputDto availableBookingOutputDto = new AvailableBookingOutputDto();

        availableBookingOutputDto.setDepartmentHour( busEntityList.getHour() );
        availableBookingOutputDto.setDepartmentDate( busEntityList.getDate() );
        availableBookingOutputDto.setDestinationCity( busEntityList.getCity() );
        availableBookingOutputDto.setSeatsAvailable( busEntityList.getAvailableSeats() );

        return availableBookingOutputDto;
    }
}
