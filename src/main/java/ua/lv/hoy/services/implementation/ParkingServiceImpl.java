package ua.lv.hoy.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lv.hoy.dao.AbstractDao;
import ua.lv.hoy.dao.CustomerDao;
import ua.lv.hoy.dao.HouseDao;
import ua.lv.hoy.dao.ParkingDao;
import ua.lv.hoy.entity.Customer;
import ua.lv.hoy.entity.Parking;
import ua.lv.hoy.services.ParkingService;

import java.util.List;

/**
 * Created by Administrator on 05-Mar-17.
 */
@Service
@Transactional
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    ParkingDao parkingDao;
    @Autowired
    CustomerDao customerDao;
    @Autowired
    HouseDao houseDao;
    @Autowired
    AbstractDao abstractDao;

    @Override
    public void add(Parking parking, int houseId) {

        if(parking.getNumber() !=0 ) {
            parking.setStatus("free");
            parking.setHouse(houseDao.findById(houseId));
            abstractDao.add(parking);
        }
    }

    @Override
    public void edit(int id, Parking editedParking) {
        Parking parking = parkingDao.findById(id);
        if (editedParking.getNumber() > 0){
            parking.setNumber(editedParking.getNumber());
        }
        if (editedParking.getStatus() != null  && !editedParking.getStatus().equalsIgnoreCase("")){
            parking.setStatus(editedParking.getStatus());
        }

        abstractDao.edit(parking);
    }

    @Override
    public void buy(int parkingId, int customerId) {
        if(parkingId != -1){
            Customer customer = customerDao.findById(customerId);
            Parking parking = parkingDao.findById(parkingId);

            if (!parking.getStatus().equalsIgnoreCase("sold") ){
                parking.setStatus("sold");
                parking.setCustomer(customer);
                abstractDao.edit(parking);
            }
        }

    }

    @Override
    public void takeParking(int parkingId, int customerId) {
        if (parkingId != -1){
            Customer customer = customerDao.findById(customerId);
            Parking parking = parkingDao.findById(parkingId);

            if(parking.getCustomer().equals(customer)){
                parking.setStatus("free");
                parking.setCustomer(null);
            }

            abstractDao.edit(parking);
        }
    }

    @Override
    public void delete(int id) {
        Parking parking = parkingDao.findById(id);
        if(parking.getStatus().equalsIgnoreCase("free")){
            parkingDao.delete(id);
        }
    }

    @Override
    public Parking findById(int id) {
        return parkingDao.findById(id);
    }

    @Override
    public Parking findByNumber(int number) {
        return parkingDao.findByNumber(number);
    }

    @Override
    public List<Parking> findFreeParkings(int houseId) {
        return parkingDao.findFreeParkingsInHouse(houseId);
    }

    @Override
    public List<Parking> findAllByCustomerId(int id) {
        return parkingDao.findByCustomerId(id);
    }

    @Override
    public List<Parking> findAllParkingsInHouse(int houseId) {
        return  parkingDao.findAllParkingInHouse(houseId);
    }
}
