package ua.lv.hoy.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 25-Feb-17.
 */
@Entity
public class Pantry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private int number;
    @Column
    private String floor;
    @Column
    private double projectSize;
    @Column
    private double realSize;
    @Column
    private String status;
    @Column
    private String description;

    @ManyToOne
    private House house;

    @ManyToOne
    private Customer customer;

    public Pantry() {
    }

    public Pantry(int number, String floor, double projectSize, double realSize, String status, String description) {
        this.number = number;
        this.floor = floor;
        this.projectSize = projectSize;
        this.realSize = realSize;
        this.status = status;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFloor() {
        return floor;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public double getProjectSize() {
        return projectSize;
    }

    public void setProjectSize(double projectSize) {
        this.projectSize = projectSize;
    }

    public double getRealSize() {
        return realSize;
    }

    public void setRealSize(double realSize) {
        this.realSize = realSize;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
