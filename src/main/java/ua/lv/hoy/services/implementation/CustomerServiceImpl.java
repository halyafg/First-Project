package ua.lv.hoy.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lv.hoy.dao.CustomerDao;
import ua.lv.hoy.dao.FlatDao;
import ua.lv.hoy.dao.PantryDao;
import ua.lv.hoy.dao.ParkingDao;
import ua.lv.hoy.entity.Customer;
import ua.lv.hoy.entity.Flat;
import ua.lv.hoy.entity.Pantry;
import ua.lv.hoy.entity.Parking;
import ua.lv.hoy.services.CustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 04-Mar-17.
 */
@Service("customerServiceImpl")
@Transactional
public class CustomerServiceImpl implements CustomerService, UserDetailsService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private FlatDao flatDao;
    @Autowired
    private PantryDao pantryDao;
    @Autowired
    private ParkingDao  parkingDao;

    public void add(String name, String surname, String lastname, String phone, String email, String password, String pasportSeria, String pasportNumber, String pasportKimVidan, String pasportData) {
        customerDao.add(new Customer(name, surname, lastname, phone, email, password,
                pasportSeria, pasportNumber, pasportKimVidan, pasportData));

    }

    public void edit(int id, String name, String surname, String lastname, String phone, String email, String password,
                     String pasportSeria, String pasportNumber, String pasportKimVidan, String pasportData) {

        Customer customer = customerDao.findById(id);

        if (name != null && !name.equalsIgnoreCase("")){
            customer.setName(name);
        }
        if (surname != null && !surname.equalsIgnoreCase("")){
            customer.setSurname(surname);
        }
        if (lastname != null && !lastname.equalsIgnoreCase("")){
            customer.setLastname(lastname);
        }
        if (phone != null && !phone.equalsIgnoreCase("")){
            customer.setPhone(phone);
        }
        if (email != null && !email.equalsIgnoreCase("")){
            customer.setEmail(email);
        }
        if (password != null && !password.equalsIgnoreCase("")){
            customer.setPassword(password);
        }

        if (pasportSeria != null && !pasportSeria.equalsIgnoreCase("")){
            customer.setPasportSeria(pasportSeria);
        }
        if (pasportNumber != null && !pasportNumber.equalsIgnoreCase("")){
            customer.setPasportNumber(pasportNumber);
        }
        if (pasportKimVidan != null && !pasportKimVidan.equalsIgnoreCase("")){
            customer.setPasportKimVidan(pasportKimVidan);
        }
        if (pasportData != null && !pasportData.equalsIgnoreCase("")){
            customer.setPasportData(pasportData);
        }

        customerDao.edit(customer);

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

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Customer customer = customerDao.findByLogin(login);
        List<GrantedAuthority>authorities = new ArrayList<GrantedAuthority>();
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
}
