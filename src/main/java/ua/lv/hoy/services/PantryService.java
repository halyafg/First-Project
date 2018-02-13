package ua.lv.hoy.services;


import ua.lv.hoy.entity.Pantry;

import java.util.List;

/**
 * Created by Administrator on 05-Mar-17.
 */
public interface PantryService {
    void add(int houseId, Pantry pantry);

    void edit(int pantryId, Pantry editedPantry);

    void buy(int pantryId,  int customerId);

    void takeOut(int pantryId,  int customerId);

    void delete(int id);

    Pantry findById(int id);

    List<Pantry> findAllPantries();

    Pantry findByNumber(int number);

    List<Pantry>fiindFreePantriesInHouse(int houseId);

    List<Pantry>findByCustomerId(int id);

    List<Pantry>findAllPantriesInHouse(int houseId);


}
