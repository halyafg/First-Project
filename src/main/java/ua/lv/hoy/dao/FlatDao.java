package ua.lv.hoy.dao;

import ua.lv.hoy.entity.Flat;

import java.util.List;

/**
 * Created by Administrator on 26-Feb-17.
 */
public interface FlatDao {

    void delete(int id);

    Flat findById (int id);

    List<Flat> findAllFlats();

    List<Flat> findFreeFlatsInHouse(int houseId);

    Flat findByNumber(int number);

    List<Flat> findByCustomerId(int customerId);

    List<Flat> findAllFlatsInHouse(int houseId);

}
