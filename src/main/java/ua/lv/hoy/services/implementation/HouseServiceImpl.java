package ua.lv.hoy.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lv.hoy.dao.AbstractDao;
import ua.lv.hoy.dao.FlatDao;
import ua.lv.hoy.dao.HouseDao;
import ua.lv.hoy.entity.Flat;
import ua.lv.hoy.entity.House;
import ua.lv.hoy.services.HouseService;

import java.util.List;

/**
 * Created by Administrator on 07-Apr-17.
 */
@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseDao houseDao;
    @Autowired
    private FlatDao flatDao;
    @Autowired
    private AbstractDao abstractDao;


    @Override
    public void add(String name, String address, String  description) {
        House house = new House(name, address, description);
        if(name.equalsIgnoreCase("")){
            house.setName("no name");
        }
        if(address.equalsIgnoreCase("")){
            house.setAddress("no address");
        }
        if(description.equalsIgnoreCase("")){
            house.setDescription("no description");
        }
        abstractDao.add(house);
    }

    @Override
    public void edit(int id, String name, String address, String description) {
        House house =houseDao.findById(id);
        if(!name.equalsIgnoreCase("")){
            house.setName(name);
        }
        if(!address.equalsIgnoreCase("")){
            house.setAddress(address);
        }
        if(!description.equalsIgnoreCase("")){
            house.setDescription(description);
        }
        abstractDao.edit(house);

    }

    @Override
    public void delete(int id) {
        House house = houseDao.findById(id);
        if(house.getFlatList().isEmpty() && house.getParkingList().isEmpty() && house.getPantryList().isEmpty()) {
            houseDao.delete(id);
        }
    }

    @Override
    public House findById(int id) {
        return houseDao.findById(id);
    }

    @Override
    public List<House> findAll() {
        return houseDao.findAll();
    }

    @Override
    public void addFlatToHouse(int houseId, int number, int floor, int rooms,
                               double projectSize, double realSize, String description){

        House house = houseDao.findById(houseId);
        Flat fl = new Flat(number, floor, rooms, projectSize, realSize, "free", description);
        fl.setHouse(house);
        if(fl.getRomsNumber()>0 && fl.getRomsNumber() <= 3){
            abstractDao.add(fl);
            //flatDao.add(fl);
        }

    }
}

