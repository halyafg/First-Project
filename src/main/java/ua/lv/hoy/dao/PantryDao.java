package ua.lv.hoy.dao;

import ua.lv.hoy.entity.Pantry;

import java.util.List;

/**
 * Created by Administrator on 26-Feb-17.
 */
public interface PantryDao {

    void delete(int id);

    Pantry findById (int id);

    List<Pantry> findAllPantries();

    List<Pantry> findFreePantriesInHouse(int houseId);

    Pantry findByNumber(int number);

    List<Pantry>findByCustomerId(int id);

    List<Pantry> findAllPantriesInHouse(int houseId);
}
