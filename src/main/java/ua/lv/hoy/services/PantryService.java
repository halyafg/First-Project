package ua.lv.hoy.services;


import ua.lv.hoy.entity.Pantry;

import java.util.List;

/**
 * Created by Administrator on 05-Mar-17.
 */
public interface PantryService {
    void add(int houseId, int number,String floor, double projectSize,
             double realSize,  String description);

    void edit(int id, int number, String floor, double projectSize,
              double realSize, String status, String description);

    void buy(int pantryId,  int customer_id);

    void takeOut(int pantryId,  int customer_id);

    void delete(int id);

    Pantry findById(int id);

    List<Pantry> findAllPantries();

    Pantry findByNumber(int number);

    List<Pantry>fiindFreePantriesInHouse(int houseId);

    List<Pantry>findByCustomerId(int id);

    List<Pantry>findAllPantriesInHouse(int houseId);


}
