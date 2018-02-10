package ua.lv.hoy.dao;

import ua.lv.hoy.entity.House;

import java.util.List;

/**
 * Created by Administrator on 07-Apr-17.
 */
public interface HouseDao{
    void add(House house);
    void edit(House house);
    void delete(int id);
    House findById (int id);
    List<House> findAll();

}
