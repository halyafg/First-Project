package ua.lv.hoy.services;

import ua.lv.hoy.entity.Payment;

import java.util.List;

/**
 * Created by Administrator on 05-Mar-17.
 */
public interface PaymentService {

    void add(int houseId, int customerId, Payment payment);

    void edit(int id, String data, double amountGRN, double quoteUSA, double amountUSA);

    void delete(int id);

    Payment findById(int id);

    List<Payment> findAllPayments();

    List<Payment> findAllPaymentsInHouse(int houseId);

    List<Payment> findPaymentsByCustomerEmail(String email);

    double paymentAmount (int custId);

    double paymentAmount (String userLogin);
}
