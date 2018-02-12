package ua.lv.hoy.services.implementation;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lv.hoy.dao.AbstractDao;
import ua.lv.hoy.dao.CustomerDao;
import ua.lv.hoy.dao.PaymentDao;
import ua.lv.hoy.entity.Customer;
import ua.lv.hoy.entity.Payment;
import ua.lv.hoy.services.PaymentService;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 05-Mar-17.
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;
    @Autowired
    CustomerDao customerDao;
    @Autowired
    AbstractDao abstractDao;

    public void add(String data, double amountGRN, double quoteUSA, double amountUSA) {
        abstractDao.add(new Payment(data, amountGRN, quoteUSA, amountUSA));
    }

    public void add(int customerId, String data, double amountGRN) {
        if(customerId != 0 && data!=null && amountGRN != 0){

            Document doc;
            String inf=null;
            try {
                doc = Jsoup.connect("https://finance.ua/ua/currency").get();
                Element kursUSD = doc.select(".major").get(0);
                inf = kursUSD.text();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String quoteUSD = inf.substring(12, 19);

            Double quote = new Double(quoteUSD);
            Payment payment = new Payment();
            payment.setCustomer(customerDao.findById(customerId));
            payment.setData(data);
            payment.setAmountGRN(amountGRN);
            payment.setQuoteUSA(quote);

            String amountString = String.format("%1$.2f",amountGRN/quote);
            double amountDouble = Double.parseDouble(amountString);
            payment.setAmountUSA(amountDouble);

            abstractDao.add(payment);
        }

    }

    public void edit(int id, String data, double amountGRN, double quoteUSA, double amountUSA) {
        Payment payment = paymentDao.findById(id);
        double quote = 27.13;

        if (data != null  && !data.equalsIgnoreCase("")){
            payment.setData(data);
        }
        if (amountGRN > 0){
            String amountGrnString = String.format("%.2f",amountGRN);
            double amountGrnDouble = Double.parseDouble(amountGrnString);

            payment.setAmountGRN(amountGrnDouble);
        }
        if (amountUSA > 0){
            String amountString = String.format("%1$.2f",amountGRN/quote);
            double amountDouble = Double.parseDouble(amountString);
            payment.setAmountUSA(amountDouble);
        }
        if (quoteUSA > 0){
            payment.setQuoteUSA(quoteUSA);
        }

        abstractDao.edit(payment);
    }

    public void delete(int id) {
                    paymentDao.delete(id);
    }

    public Payment findById(int id) {
        return paymentDao.findById(id);
    }

    public List<Payment> findAllPayments() {
        return paymentDao.findAllPayments();
    }

    public List<Payment> findPaymentsByCustomerEmail(String email) {
        return paymentDao.findAllCustomerPayments(email);
    }

    public double paymentAmount (int custId){
        Customer customer = customerDao.findById(custId);
        List<Payment>paymentList = paymentDao.findAllCustomerPayments(customer.getEmail());
        double amount = 0;
        for (Payment p: paymentList ) {
            amount += p.getAmountUSA();
        }
        return amount;
    }
    public double paymentAmount (String userLogin){
        Customer customer = customerDao.findByLogin(userLogin);
        List<Payment>paymentList = paymentDao.findAllCustomerPayments(customer.getEmail());
        double amount = 0;
        for (Payment p: paymentList ) {
            amount += p.getAmountUSA();
        }
        return amount;
    }
}
