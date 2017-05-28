package ua.lv.hoy.services;

import ua.lv.hoy.dao.HouseDao;
import ua.lv.hoy.entity.Flat;
import ua.lv.hoy.entity.House;

import java.util.List;

/**
 * Created by Administrator on 07-Apr-17.
 */
public interface HouseService {
    void add(String name, String address, String  description);
    void edit(int id, String name, String address, String description);
    void delete (int id);

    House findById(int id);

    List<House> findAll();

    void addFlatToHouse(int houseId, int number, int floor, int rooms, double projectSize, double realSize, String description);

}
