package ua.lv.hoy.services;

import ua.lv.hoy.entity.Customer;

import java.util.List;

/**
 * Created by Administrator on 26-Feb-17.
 */
public interface CustomerService {
    void add(String name, String surname, String lastname,
             String phone, String email, String password,
             String pasportSeria, String pasportNumber, String pasportKimVidan, String pasportData);

    void edit(int id, String name, String surname, String lastname,
              String phone, String email, String password,
              String pasportSeria, String pasportNumber, String pasportKimVidan, String pasportData);

    void delete(int id);

    Customer findById (int id);

    Customer findCustomerByLogin(String login);

    List<Customer> findAllCustomers();

    List<Customer> findAllCustomersInHouse(int houseId);
}
