package ua.lv.hoy.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lv.hoy.dao.CustomerDao;
import ua.lv.hoy.dao.HouseDao;
import ua.lv.hoy.dao.PantryDao;
import ua.lv.hoy.entity.Customer;
import ua.lv.hoy.entity.Pantry;
import ua.lv.hoy.services.PantryService;

import java.util.List;

/**
 * Created by Administrator on 05-Mar-17.
 */
@Service
@Transactional
public class PantryServiceImpl implements PantryService {

    @Autowired
    private PantryDao pantryDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private HouseDao houseDao;

    public void add(int houseId, int number, String floor,  double projectSize, double realSize,  String description) {
        Pantry pantry = new Pantry(number, floor, projectSize, realSize, "free", description);
        pantry.setHouse(houseDao.findById(houseId));
        pantryDao.add(pantry);
    }

    public void edit(int id, int number, String floor,  double projectSize, double realSize, String status, String description) {
        Pantry pantry = pantryDao.findById(id);

        if (floor != null  && !floor.equalsIgnoreCase("")){
            pantry.setFloor(floor);
        }
        if (number >0){
            pantry.setNumber(number);
        }
        if (projectSize >0){
            pantry.setProjectSize(projectSize);
        }
        if (realSize >0){
            pantry.setRealSize(realSize);
        }
        if (status != null  && !status.equalsIgnoreCase("")){
            pantry.setStatus(status);
        }
        if (description != null  && !description.equalsIgnoreCase("")){
            pantry.setDescription(description);
        }

        pantryDao.edit(pantry);
    }
    public void buy (int pantryId, int customerId){
        if(pantryId!= -1){
            Customer customer = customerDao.findById(customerId);
            Pantry pantry = pantryDao.findById(pantryId);
            if(!pantry.getStatus().equalsIgnoreCase("sold")){
                pantry.setStatus("sold");
                pantry.setCustomer(customer);
            }
            pantryDao.edit(pantry);
        }
    }

    public void takeOut(int pantryId, int customerId) {
        if(pantryId != -1){
            Customer customer = customerDao.findById(customerId);
            Pantry pantry = pantryDao.findById(pantryId);
            if(pantry.getCustomer().equals(customer)){
                pantry.setStatus("free");
                pantry.setCustomer(null);
            }

            pantryDao.edit(pantry);
        }
    }

    public void delete(int id) {
        Pantry pantry = pantryDao.findById(id);
        if(pantry.getStatus().equalsIgnoreCase("free")){
            pantryDao.delete(id);
        }
    }

    public Pantry findById(int id) {
        return pantryDao.findById(id);
    }

    public List<Pantry> findAllPantries() {
        return pantryDao.findAllPantries();
    }

    public Pantry findByNumber(int number) {
        return pantryDao.findByNumber(number);
    }

    public List<Pantry> fiindFreePantriesInHouse(int houseId) {
        return pantryDao.findFreePantriesInHouse(houseId);
    }

    public List<Pantry> findByCustomerId(int id) {
        return pantryDao.findByCustomerId(id);
    }

    @Override
    public List<Pantry> findAllPantriesInHouse(int houseId) {
        return pantryDao.findAllPantriesInHouse(houseId);
    }
}
