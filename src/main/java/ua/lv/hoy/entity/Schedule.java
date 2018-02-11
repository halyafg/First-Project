package ua.lv.hoy.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 25-Feb-17.
 */
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String  date;
    @Column
    private double amountUSA;

    @ManyToOne
    private Customer customer;

    public Schedule() {
    }

    public Schedule(String date, double amountUSA) {
        this.date = date;
        this.amountUSA = amountUSA;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
