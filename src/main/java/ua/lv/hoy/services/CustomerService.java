package ua.lv.hoy.services;

import ua.lv.hoy.entity.Customer;

import java.util.List;

/**
 * Created by Administrator on 26-Feb-17.
 */
public interface CustomerService {
    void add(Customer customer);

    void edit(int customerId, Customer newCustomer);

    void delete(int id);

    Customer findById (int id);

    Customer findCustomerByLogin(String login);

    List<Customer> findAllCustomers();

    List<Customer> findAllCustomersInHouse(int houseId);
}
