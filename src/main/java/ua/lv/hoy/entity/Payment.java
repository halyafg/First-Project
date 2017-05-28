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
    private double amount_grn;
    @Column
    private double quote_$;
    @Column
    private double amount_$;

    @ManyToOne
    private Customer customer;

    public Payment() {
    }

    public Payment(String data, double amount_grn, double quote_$, double amount_$) {
        this.data = data;
        this.amount_grn = amount_grn;
        this.quote_$ = quote_$;
        this.amount_$ = amount_$;
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

    public double getAmount_grn() {
        return amount_grn;
    }

    public void setAmount_grn(double amount_grn) {
        this.amount_grn = amount_grn;
    }

    public double getQuote_$() {
        return quote_$;
    }

    public void setQuote_$(double quote_$) {
        this.quote_$ = quote_$;
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
