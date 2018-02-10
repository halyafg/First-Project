package ua.lv.hoy.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Administrator on 25-Feb-17.
 */
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String lastname;
    @Column(unique = true)
    private String phone;
    @Column(unique = true)
    private String email;
    @Column(unique = true, nullable = false)
    private String password;

    @Column
    private String pasportSeria;
    @Column
    private String pasportNumber;
    @Column
    private String pasportKimVidan;
    @Column
    private String pasportData;

    @OneToMany(mappedBy = "customer" , fetch = FetchType.EAGER)
    private List<Flat> flatList;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Pantry> pantryList;

    @OneToMany(mappedBy = "customer" , fetch = FetchType.EAGER)
    private List<Parking>parkingList;

    @OneToMany(mappedBy = "customer")
    private List<Schedule> scheduleList;

    @OneToMany(mappedBy = "customer")
    private List<Payment> paymentList;


    public Customer() {
    }

    public Customer(String name, String surname, String lastname, String phone, String email, String password,
                    String pasportSeria, String pasportNumber, String pasportKimVidan, String pasportData) {
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.pasportSeria = pasportSeria;
        this.pasportNumber = pasportNumber;
        this.pasportKimVidan = pasportKimVidan;
        this.pasportData = pasportData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPasportSeria() {
        return pasportSeria;
    }

    public void setPasportSeria(String pasportSeria) {
        this.pasportSeria = pasportSeria;
    }

    public String getPasportNumber() {
        return pasportNumber;
    }

    public void setPasportNumber(String pasportNumber) {
        this.pasportNumber = pasportNumber;
    }

    public String getPasportKimVidan() {
        return pasportKimVidan;
    }

    public void setPasportKimVidan(String pasportKimVidan) {
        this.pasportKimVidan = pasportKimVidan;
    }

    public String getPasportData() {
        return pasportData;
    }

    public void setPasportData(String pasportData) {
        this.pasportData = pasportData;
    }

    public List<Flat> getFlatList() {
        return flatList;
    }

    public void setFlatList(List<Flat> flatList) {
        this.flatList = flatList;
    }

    public List<Pantry> getPantryList() {
        return pantryList;
    }

    public void setPantryList(List<Pantry> pantryList) {
        this.pantryList = pantryList;
    }

    public List<Parking> getParkingList() {
        return parkingList;
    }

    public void setParkingList(List<Parking> parkingList) {
        this.parkingList = parkingList;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }
}
