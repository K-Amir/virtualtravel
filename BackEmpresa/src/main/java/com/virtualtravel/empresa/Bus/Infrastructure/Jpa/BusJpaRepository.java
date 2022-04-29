package com.virtualtravel.empresa.Bus.Infrastructure.Jpa;

import com.virtualtravel.empresa.Bus.Domain.BusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface BusJpaRepository extends JpaRepository<BusEntity, Integer> {

    // Gets bus with the specified destiny and then we have to update it removing one seat
    BusEntity findBusEntityByDateIsAndCityIsAndHourIs(Date date, String city, String hour);

}
