package ua.lv.hoy.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Administrator on 07-Apr-17.
 */
@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private  int id;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String description;


    @OneToMany(mappedBy = "house" , fetch = FetchType.EAGER)
    private List<Flat> flatList;

    @OneToMany(mappedBy = "house" , fetch = FetchType.EAGER)
    private List<Pantry>pantryList;

    @OneToMany(mappedBy = "house" , fetch = FetchType.EAGER)
    private List<Parking> parkingList;


    public House() {
    }

    public House(String name, String address, String description) {
        this.name = name;
        this.address = address;
        this.description = description;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
