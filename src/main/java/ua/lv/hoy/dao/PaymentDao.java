package ua.lv.hoy.dao;

import ua.lv.hoy.entity.Payment;

import java.util.List;

/**
 * Created by Administrator on 26-Feb-17.
 */
public interface PaymentDao {

    void delete(int id);

    Payment findById (int id);

    List<Payment> findAllPayments();

    List<Payment> findAllPaymentsInHouse(int houseId);

    List<Payment> findAllCustomerPayments(String email);
}
