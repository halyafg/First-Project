package ua.lv.hoy.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 25-Feb-17.
 */
@Entity
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private int number;
    @Column
    private String status;

    @ManyToOne
    private House house;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;


    public Parking() {
    }

    public Parking(int number, String status) {
        this.number = number;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
}
