package ua.lv.hoy.services.implementation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lv.hoy.dao.AbstractDao;
import ua.lv.hoy.dao.CustomerDao;
import ua.lv.hoy.dao.HouseDao;
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
    @Autowired
    HouseDao houseDao;

    public void add(int houseId, int customerId, Payment payment) {
        if(customerId != 0 && payment.getData()!=null && payment.getAmountGRN() != 0){
            double quote = getQuoteUSA();
            if(quote != 0.0){
                payment.setHouse(houseDao.findById(houseId));
                payment.setCustomer(customerDao.findById(customerId));
                payment.setQuoteUSA(quote);

                String amountString = String.format("%1$.2f",payment.getAmountGRN()/quote);
                double amountDouble = Double.parseDouble(amountString);
                payment.setAmountUSA(amountDouble);

                abstractDao.add(payment);
            }
        }

    }

    @Override
    public void edit(int paymentId, Payment editedPayment) {
        Payment payment = paymentDao.findById(paymentId);
        if (editedPayment.getData() != null  && !editedPayment.getData().equalsIgnoreCase("")){
            payment.setData(editedPayment.getData());
        }
        if (editedPayment.getAmountGRN() > 0){
            String amountGrnString = String.format("%.2f",editedPayment.getAmountGRN());
            double amountGrnDouble = Double.parseDouble(amountGrnString);
            payment.setAmountGRN(amountGrnDouble);
        }
        if (editedPayment.getQuoteUSA() > 0){
            payment.setQuoteUSA(editedPayment.getQuoteUSA());
            String amountString = String.format("%1$.2f",payment.getAmountGRN()/editedPayment.getQuoteUSA());
            double amountDouble = Double.parseDouble(amountString);
            payment.setAmountUSA(amountDouble);
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

    @Override
    public List<Payment> findAllPaymentsInHouse(int houseId) {
        return paymentDao.findAllPaymentsInHouse(houseId);
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

    private static double getQuoteUSA (){
        final Logger logger = LogManager.getLogger(PaymentServiceImpl.class.getName());
        Document doc;
        String inf=null;
        try {
            doc = Jsoup.connect("https://finance.ua/ua/currency").get();
            Element kursUSD = doc.select(".major").get(0);
            inf = kursUSD.text();
        } catch (IOException e) {
            logger.fatal("An exception occurred while getting the quoteUSA", e);
        }finally {
            if(inf == null){
                inf = "000000000000.0000000";
            }
        }
        String quoteUSD = inf.substring(12, 19);;
        
        return Double.parseDouble(quoteUSD);
    }
}
