package ua.lv.hoy.services;

import ua.lv.hoy.entity.Customer;
import ua.lv.hoy.entity.Payment;

import java.util.List;

/**
 * Created by Administrator on 05-Mar-17.
 */
public interface PaymentService {
    void add(String data, double amount_grn, double quote_$, double amount_$);

    void add(int customer_id, String data, double amount_grn);

    void edit(int id, String data, double amount_grn, double quote_$, double amount_$);

    void delete(int id);

    Payment findById(int id);

    List<Payment> findAllPayments();

    List<Payment> findPaymentsByCustomerEmail(String email);

    double paymentAmount (int cust_id);

    double paymentAmount (String user_login);
}
