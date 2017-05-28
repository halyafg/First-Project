package ua.lv.hoy.services.implementation;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public void add(String data, double amount_grn, double quote_$, double amount_$) {
        paymentDao.add(new Payment(data, amount_grn, quote_$, amount_$));
    }

    public void add(int customer_id, String data, double amount_grn) {
        if(customer_id != 0 && data!=null && amount_grn != 0){

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
            payment.setCustomer(customerDao.findById(customer_id));
            payment.setData(data);
            payment.setAmount_grn(amount_grn);
            payment.setQuote_$(quote);

            String amountString = String.format("%1$.2f",amount_grn/quote);
            double amountDouble = Double.parseDouble(amountString);
            payment.setAmount_$(amountDouble);

            paymentDao.add(payment);
        }

    }

    public void edit(int id, String data, double amount_grn, double quote_$, double amount_$) {
        Payment payment = paymentDao.findById(id);
        double quote = 27.13;

        if (data != null  && !data.equalsIgnoreCase("")){
            payment.setData(data);
        }
        if (amount_grn > 0){
            String amountGrnString = String.format("%.2f",amount_grn);
            double amountGrnDouble = Double.parseDouble(amountGrnString);

            payment.setAmount_grn(amountGrnDouble);
        }
        if (amount_$ > 0){
            String amountString = String.format("%1$.2f",amount_grn/quote);
            double amountDouble = Double.parseDouble(amountString);
            payment.setAmount_$(amountDouble);
        }
        if (quote_$ > 0){
            payment.setQuote_$(quote_$);
        }

        paymentDao.edit(payment);
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

    public double paymentAmount (int cust_id){
        Customer customer = customerDao.findById(cust_id);
        List<Payment>paymentList = paymentDao.findAllCustomerPayments(customer.getEmail());
        double amount = 0;
        for (Payment p: paymentList ) {
            amount += p.getAmount_$();
        }
        return amount;
    }
    public double paymentAmount (String user_login){
        Customer customer = customerDao.findByLogin(user_login);
        List<Payment>paymentList = paymentDao.findAllCustomerPayments(customer.getEmail());
        double amount = 0;
        for (Payment p: paymentList ) {
            amount += p.getAmount_$();
        }
        return amount;
    }
}
