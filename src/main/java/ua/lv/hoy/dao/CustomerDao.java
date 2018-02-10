package ua.lv.hoy.dao;

import ua.lv.hoy.entity.Customer;

import java.util.List;

/**
 * Created by Administrator on 26-Feb-17.
 */
public interface CustomerDao {
    void add(Customer customer);

    void edit(Customer customer);

    void delete(int id);

    Customer findById (int id);

    List<Customer> findAllCustomers();

    Customer findByLogin(String login);

}
