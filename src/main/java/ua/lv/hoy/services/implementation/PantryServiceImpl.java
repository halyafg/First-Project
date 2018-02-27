package ua.lv.hoy.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lv.hoy.dao.AbstractDao;
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
    PantryDao pantryDao;
    @Autowired
    CustomerDao customerDao;
    @Autowired
    HouseDao houseDao;
    @Autowired
    AbstractDao abstractDao;

    @Override
    public void add(int houseId, Pantry pantry) {
        pantry.setHouse(houseDao.findById(houseId));
        abstractDao.add(pantry);
    }

    @Override
    public void edit(int id, Pantry editedPantry) {
        Pantry pantry = pantryDao.findById(id);

        if (editedPantry.getFloor() != null  && !editedPantry.getFloor().equalsIgnoreCase("")){
            pantry.setFloor(editedPantry.getFloor());
        }
        if (editedPantry.getNumber() >0){
            pantry.setNumber(editedPantry.getNumber() );
        }
        if (editedPantry.getProjectSize()  >0){
            pantry.setProjectSize(editedPantry.getProjectSize());
        }
        if (editedPantry.getRealSize()>0){
            pantry.setRealSize(editedPantry.getRealSize());
        }
        if (editedPantry.getStatus()!= null  && !editedPantry.getStatus().equalsIgnoreCase("")){
            pantry.setStatus(editedPantry.getStatus());
        }
        if (editedPantry.getDescription() != null  && !editedPantry.getDescription().equalsIgnoreCase("")){
            pantry.setDescription(editedPantry.getDescription());
        }

        abstractDao.edit(pantry);
    }

    @Override
    public void buy (int pantryId, int customerId){
        if(pantryId!= -1){
            Customer customer = customerDao.findById(customerId);
            Pantry pantry = pantryDao.findById(pantryId);
            if(!pantry.getStatus().equalsIgnoreCase("sold")){
                pantry.setStatus("sold");
                pantry.setCustomer(customer);
            }
            abstractDao.edit(pantry);
        }
    }

    @Override
    public void takeOut(int pantryId, int customerId) {
        if(pantryId != -1){
            Customer customer = customerDao.findById(customerId);
            Pantry pantry = pantryDao.findById(pantryId);
            if(pantry.getCustomer().equals(customer)){
                pantry.setStatus("free");
                pantry.setCustomer(null);
            }

            abstractDao.edit(pantry);
        }
    }

    @Override
    public void delete(int id) {
        Pantry pantry = pantryDao.findById(id);
        if(pantry.getStatus().equalsIgnoreCase("free")){
            pantryDao.delete(id);
        }
    }

    @Override
    public Pantry findById(int id) {
        return pantryDao.findById(id);
    }

    @Override
    public List<Pantry> findAllPantries() {
        return pantryDao.findAllPantries();
    }

    @Override
    public Pantry findByNumber(int number) {
        return pantryDao.findByNumber(number);
    }

    @Override
    public List<Pantry> fiindFreePantriesInHouse(int houseId) {
        return pantryDao.findFreePantriesInHouse(houseId);
    }

    @Override
    public List<Pantry> findByCustomerId(int id) {
        return pantryDao.findByCustomerId(id);
    }

    @Override
    public List<Pantry> findAllPantriesInHouse(int houseId) {
        return pantryDao.findAllPantriesInHouse(houseId);
    }
}
