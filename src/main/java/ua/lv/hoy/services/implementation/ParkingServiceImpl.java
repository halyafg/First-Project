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
    private ParkingDao parkingDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private HouseDao houseDao;
    @Autowired
    AbstractDao abstractDao;

    public void add(Parking parking, int houseId) {

        if(parking.getNumber() !=0 ) {
            parking.setStatus("free");
            parking.setHouse(houseDao.findById(houseId));
            abstractDao.add(parking);
        }
    }

    public void edit(int id, int number, String status) {
        Parking parking = parkingDao.findById(id);

        if (number > 0){
            parking.setNumber(number);
        }
        if (status != null  && !status.equalsIgnoreCase("")){
            parking.setStatus(status);
        }

        abstractDao.edit(parking);

    }

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

    public void delete(int id) {
        Parking parking = parkingDao.findById(id);
        if(parking.getStatus().equalsIgnoreCase("free")){
            parkingDao.delete(id);
        }
    }

    public Parking findById(int id) {
        return parkingDao.findById(id);
    }

    public Parking findByNumber(int number) {
        return parkingDao.findByNumber(number);
    }

    public List<Parking> findAllPantries() {
        return parkingDao.findAllParkings();
    }

    public List<Parking> findFreeParkings(int houseId) {
        return parkingDao.findFreeParkingsInHouse(houseId);
    }

    public List<Parking> findAllByCustomerId(int id) {
        return parkingDao.findByCustomerId(id);
    }

    @Override
    public List<Parking> findAllParkingsInHouse(int houseId) {
        return  parkingDao.findAllParkingInHouse(houseId);
    }
}
