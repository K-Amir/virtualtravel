package com.virtualtravel.web.Bus.Domain;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface BusService {

    void createBus(BusEntity busEntity);

    BusEntity findBusById(int id);

    List<BusEntity> findAllBuses();

    void deleteBusById(int id);

    void findBusToBookAndDecreaseSeat(Date date, String city, String hour);

    void syncBusAvailableSeats(BusEntity busEntity);

    List<BusEntity> getAvailableBookings(String city, HashMap<String, Object> params);
}
