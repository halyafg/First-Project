package ua.lv.hoy.dao;


import ua.lv.hoy.entity.Parking;

import java.util.List;

/**
 * Created by Administrator on 26-Feb-17.
 */
public interface ParkingDao {
    void add(Parking parking);

    void edit(Parking parking);

    void delete(int id);

    Parking findById (int id);

    Parking findByNumber (int number);

    List<Parking> findAllParkings();

    List<Parking> findFreeParkingsInHouse(int houseId);

    List<Parking> findByCustomerId(int id);

    List<Parking> findAllParkingInHouse(int houseId);


}
