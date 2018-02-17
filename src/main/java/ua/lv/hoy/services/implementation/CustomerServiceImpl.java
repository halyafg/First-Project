package ua.lv.hoy.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lv.hoy.dao.*;
import ua.lv.hoy.entity.Customer;
import ua.lv.hoy.entity.Flat;
import ua.lv.hoy.entity.Pantry;
import ua.lv.hoy.entity.Parking;
import ua.lv.hoy.services.CustomerService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 04-Mar-17.
 */
@Service("customerServiceImpl")
@Transactional
public class CustomerServiceImpl implements CustomerService, UserDetailsService {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private AbstractDao abstractDao;
    @Autowired
    private ParkingDao parkingDao;
    @Autowired
    private FlatDao flatDao;
    @Autowired
    private PantryDao pantryDao;

    @Override
    public void add(Customer customer) {
        if(!customer.getEmail().equalsIgnoreCase("") && !(customer.getPassword().equalsIgnoreCase(""))){
            abstractDao.add(customer);
        }
    }


    public void edit(int customrId, Customer newCustomer) {

        Customer customer = customerDao.findById(customrId);

        if (checkField(newCustomer.getName())){
            customer.setName(newCustomer.getName());
        }
        if (checkField(newCustomer.getSurname())){
            customer.setSurname(newCustomer.getSurname());
        }
        if (checkField(newCustomer.getLastname())){
            customer.setLastname(newCustomer.getLastname());
        }
        if (checkField(newCustomer.getPhone())){
            customer.setPhone(newCustomer.getPhone());
        }
        if (checkField(newCustomer.getEmail())){
            customer.setEmail(newCustomer.getEmail());
        }
        if (checkField(newCustomer.getPassword())){
            customer.setPassword(newCustomer.getPassword());
        }
        if (checkField(newCustomer.getPasportSeria())){
            customer.setPasportSeria(newCustomer.getPasportSeria());
        }
        if (checkField(newCustomer.getPasportNumber())){
            customer.setPasportNumber(newCustomer.getPasportNumber());
        }
        if (checkField(newCustomer.getPasportKimVidan())){
            customer.setPasportKimVidan(newCustomer.getPasportKimVidan());
        }
        if (checkField(newCustomer.getPasportData())){
            customer.setPasportData(newCustomer.getPasportData());
        }

        abstractDao.edit(customer);

    }

    public void delete(int id) {
        Customer customer = customerDao.findById(id);
        if(customer.getParkingList().isEmpty() && customer.getPantryList().isEmpty()  &&  customer.getFlatList().isEmpty()){
            customerDao.delete(id);
        }
    }

    public Customer findById(int id) {
        return customerDao.findById(id);
    }

    public List<Customer> findAllCustomers(){
        return customerDao.findAllCustomers();
    }

    public  Customer findCustomerByLogin(String login){
        return customerDao.findByLogin(login);
    }

    public UserDetails loadUserByUsername(String login){
        Customer customer = customerDao.findByLogin(login);
        List<GrantedAuthority>authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(customer.getEmail(), customer.getPassword(), authorities);
    }

    public List<Customer> findAllCustomersInHouse(int houseId) {
        List<Customer> customerList =  customerDao.findAllCustomers();
        List<Customer> customers = new ArrayList<>();

        for (Customer c:customerList           ) {
            if(!c.getFlatList().isEmpty()){
                List<Flat> flatList = flatDao.findByCustomerId(c.getId());
                for (Flat f: flatList               ) {
                    if(f.getHouse().getId() == houseId){
                        customers.add(c);
                        break;
                    }
                }
            }
            if(!c.getPantryList().isEmpty()){
                List<Pantry>pantryList = pantryDao.findByCustomerId(c.getId() );
                for (Pantry p: pantryList             ) {
                    if(p.getHouse().getId()  == houseId  && !customers.contains(c)){
                        customers.add(c);
                        break;
                    }
                }
            }
            if(!c.getParkingList().isEmpty()){
                List<Parking>parkingList = parkingDao.findByCustomerId(c.getId());
                for (Parking p: parkingList             ) {
                    if(p.getHouse().getId()  == houseId  && !customers.contains(c)){
                        customers.add(c);
                        break;
                    }
                }
            }

        }
        return customers;
    }


    private static boolean checkField(String field){
        /*
        INSTEAD OF:
        if( (field != null) && (!field.equalsIgnoreCase("")) ){
            return true;
        }
        return false;
        USED NEXT:
        */
        return ( (field != null) && (!field.equalsIgnoreCase("")) );
    }

}
