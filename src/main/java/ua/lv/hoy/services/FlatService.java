package ua.lv.hoy.services;


import ua.lv.hoy.entity.Flat;
import ua.lv.hoy.entity.House;
import ua.lv.hoy.entity.Image;

import java.util.List;

/**
 * Created by Administrator on 05-Mar-17.
 */
public interface FlatService {
    void add(int flatnumber, int floor, int romsNumber, double projectSize, double realSize,
             String description);

    void edit(int id, int flatnumber, int floor, int romsNumber, double projectSize, double realSize,
              String status, String description);

    void delete(int id);

    Flat findById(int id);

    List<Flat> findAllFlats();

   Flat findByNumber(int number);

    List<Flat> findByCustomerId(int customer_id);

    void buy (int flatId, int customer_id);

    void takeAway(int flatId, int customerId);

    List<Flat> findFreeFlatByRoomsNumber(int rooms, int houseId);

    List<Flat> findAllFlatsInHouse(int houseId);

    List<Flat> findFreeFlatsInHouse(int houseId);
}
