package ua.lv.hoy.services;

import ua.lv.hoy.entity.Flat;
import ua.lv.hoy.entity.House;

import java.util.List;

/**
 * Created by Administrator on 07-Apr-17.
 */
public interface HouseService {
    void add(House house);
    void edit(int houseId, House editedHouse);
    void delete (int id);

    House findById(int id);

    List<House> findAll();

}
