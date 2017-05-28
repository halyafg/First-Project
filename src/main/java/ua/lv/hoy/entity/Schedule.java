package ua.lv.hoy.entity;

import javax.persistence.*;
import java.util.Date;

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
    private double amount_$;

    @ManyToOne
    private Customer customer;

    public Schedule() {
    }

    public Schedule(String date, double amount_$) {
        this.date = date;
        this.amount_$ = amount_$;
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

    public double getAmount_$() {
        return amount_$;
    }

    public void setAmount_$(double amount_$) {
        this.amount_$ = amount_$;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
