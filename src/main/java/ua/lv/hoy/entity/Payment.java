package ua.lv.hoy.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 25-Feb-17.
 */
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String data;
    @Column
    private double amountGRN;
    @Column
    private double quoteUSA;
    @Column
    private double amountUSA;

    @ManyToOne
    private Customer customer;

    public Payment() {
    }

    public Payment(String data, double amountGRN, double quoteUSA, double amountUSA) {
        this.data = data;
        this.amountGRN = amountGRN;
        this.quoteUSA = quoteUSA;
        this.amountUSA = amountUSA;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getAmountGRN() {
        return amountGRN;
    }

    public void setAmountGRN(double amountGRN) {
        this.amountGRN = amountGRN;
    }

    public double getQuoteUSA() {
        return quoteUSA;
    }

    public void setQuoteUSA(double quoteUSA) {
        this.quoteUSA = quoteUSA;
    }

    public double getAmountUSA() {
        return amountUSA;
    }

    public void setAmountUSA(double amountUSA) {
        this.amountUSA = amountUSA;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
