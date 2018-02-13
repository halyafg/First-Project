package ua.lv.hoy.services;


import ua.lv.hoy.entity.Flat;
import java.util.List;

/**
 * Created by Administrator on 05-Mar-17.
 */
public interface FlatService {

    void addFlatToHouse(int houseId, Flat flat);

    void edit (int flatId, Flat newFlat);

    void delete(int id);

    Flat findById(int id);

    List<Flat> findAllFlats();

   Flat findByNumber(int number);

    List<Flat> findByCustomerId(int customerId);

    void buy (int flatId, int customerId);

    void takeAway(int flatId, int customerId);

    List<Flat> findFreeFlatByRoomsNumber(int rooms, int houseId);

    List<Flat> findAllFlatsInHouse(int houseId);

    List<Flat> findFreeFlatsInHouse(int houseId);
}
