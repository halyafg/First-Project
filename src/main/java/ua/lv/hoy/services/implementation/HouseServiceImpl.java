package ua.lv.hoy.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lv.hoy.dao.AbstractDao;
import ua.lv.hoy.dao.HouseDao;
import ua.lv.hoy.entity.House;
import ua.lv.hoy.services.HouseService;

import java.util.List;

/**
 * Created by Administrator on 07-Apr-17.
 */
@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    HouseDao houseDao;
    @Autowired
    AbstractDao abstractDao;


    @Override
    public void add(House house) {
        if(house.getName().equalsIgnoreCase("")){
            house.setName("no name");
        }
        if(house.getAddress().equalsIgnoreCase("")){
            house.setAddress("no address");
        }
        if(house.getDescription().equalsIgnoreCase("")){
            house.setDescription("no description");
        }
        abstractDao.add(house);
    }

    @Override
    public void edit(int houseId, House editedHouse) {
        House house =houseDao.findById(houseId);
        if(!editedHouse.getName().equalsIgnoreCase("")){
            house.setName(editedHouse.getName());
        }
        if(!editedHouse.getAddress().equalsIgnoreCase("")){
            house.setAddress(editedHouse.getAddress());
        }
        if(!editedHouse.getDescription().equalsIgnoreCase("")){
            house.setDescription(editedHouse.getDescription());
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

}

