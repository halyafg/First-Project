package ua.lv.hoy.services;

import ua.lv.hoy.entity.Parking;

import java.util.List;

/**
 * Created by Administrator on 05-Mar-17.
 */
public interface ParkingService {

    void add(Parking parking, int houseId);

    void edit(int id, Parking editedParking);

    void buy(int parkingId,  int customerId);

    void takeParking(int parkingId,  int customerId);

    void delete(int id);

    Parking findById(int id);

    Parking findByNumber(int number);

    List<Parking> findFreeParkings(int houseId);

    List<Parking>findAllByCustomerId(int id);

    List<Parking>findAllParkingsInHouse(int houseId);


}
