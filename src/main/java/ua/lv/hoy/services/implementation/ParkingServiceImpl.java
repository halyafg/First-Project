package ua.lv.hoy.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lv.hoy.dao.CustomerDao;
import ua.lv.hoy.dao.HouseDao;
import ua.lv.hoy.dao.ParkingDao;
import ua.lv.hoy.entity.Customer;
import ua.lv.hoy.entity.Pantry;
import ua.lv.hoy.entity.Parking;
import ua.lv.hoy.services.CustomerService;
import ua.lv.hoy.services.ParkingService;

import java.util.ArrayList;
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

    public void add(int number, int houseId) {
        /*List<Parking> parkingList = parkingDao.FindAllParkingInHouse(houseId);
        boolean existParking = true;
        for (Parking p:parkingList             ) {
            if(p.getNumber() == number){
                existParking = false;
            }
        }
        if(number !=0 && !existParking) {
            parkingDao.add(new Parking(number, "free"));
        }*/
        if(number !=0 ) {
            Parking parking = new Parking(number, "free");
            parking.setHouse(houseDao.findById(houseId));
            parkingDao.add(parking);
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

        parkingDao.edit(parking);

    }

    public void buy(int parkingId, int customer_id) {
        if(parkingId != -1){
            Customer customer = customerDao.findById(customer_id);
            Parking parking = parkingDao.findById(parkingId);

            if (!parking.getStatus().equalsIgnoreCase("sold") ){
                parking.setStatus("sold");
                parking.setCustomer(customer);
                parkingDao.edit(parking);
            }
        }

    }

    public void takeParking(int parkingId, int customer_id) {
        if (parkingId != -1){
            Customer customer = customerDao.findById(customer_id);
            Parking parking = parkingDao.findById(parkingId);

        /*if(!parking.getCustomer().equals(null)){*/
            if(parking.getCustomer().equals(customer)){
                parking.setStatus("free");
                parking.setCustomer(null);
          /*  }*/
            }

            parkingDao.edit(parking);
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
       /* List<Parking> parkingList = parkingDao.FindAllParkingInHouse(houseId);
        List<Parking> freeParkings = new ArrayList<>();
        if(!parkingList.isEmpty()){
            for (Parking p:parkingList     ) {
                if(p.getStatus().equalsIgnoreCase("free")){
                    freeParkings.add(p);
                }
            }
        }
        return  freeParkings;*/
    }

    public List<Parking> findAllByCustomerId(int id) {
        return parkingDao.findByCustomerId(id);
    }

    @Override
    public List<Parking> findAllParkingsInHouse(int houseId) {
        return parkingDao.FindAllParkingInHouse(houseId);
    }
}
