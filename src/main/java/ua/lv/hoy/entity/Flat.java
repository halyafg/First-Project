package ua.lv.hoy.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Administrator on 25-Feb-17.
 */
@Entity
public class Flat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private  int id;
    @Column /*(unique = true)*/
    private int flatnumber;
    @Column
    private int floor;
    @Column
    private int romsNumber;
    @Column
    private double projectSize;
    @Column
    private double realSize;
    @Column
    private String status;
    @Column
    private String description;


    @ManyToOne
    private Customer customer;

    @ManyToOne
    private House house;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "flat_image",
            joinColumns = @JoinColumn(name = "flatId"),
            inverseJoinColumns = @JoinColumn(name = "imageId"))
    private List<Image> imageList;



//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "FLAT_IMAGE",
//            joinColumns = @JoinColumn(name = "FLAT_ID", referencedColumnName="id"),
//            inverseJoinColumns = @JoinColumn(name = "IMAGE_ID", referencedColumnName="id"))
//    private List<Image> imageList;


//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "flat_image",
//            joinColumns = @JoinColumn(name = "flatId", referencedColumnName="id"),
//            inverseJoinColumns = @JoinColumn(name = "imageId", referencedColumnName="id"))
//    public List<Image> getImages(){return imageList;}



//    Example 3:
//
//    // In Customer class:
//
//    @ManyToMany
//    @JoinTable(name="CUST_PHONE",
//            joinColumns= @JoinColumn(name="CUST_ID", referencedColumnName="ID"),
//            inverseJoinColumns= @JoinColumn(name="PHONE_ID", referencedColumnName="ID")
//    )
//    public Set<PhoneNumber> getPhones() { return phones; }
//
//    // In PhoneNumberClass:
//
//    @ManyToMany(mappedBy="phones")
//    public Set<Customer> getCustomers() { return customers; }





    public Flat() {
    }

    public Flat(int flatnumber, int floor, int romsNumber, double projectSize, double realSize, String status, String description) {
        this.flatnumber = flatnumber;
        this.floor = floor;
        this.romsNumber = romsNumber;
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

    public int getFlatnumber() {
        return flatnumber;
    }

    public void setFlatnumber(int flatnumber) {
        this.flatnumber = flatnumber;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getRomsNumber() {
        return romsNumber;
    }

    public void setRomsNumber(int romsNumber) {
        this.romsNumber = romsNumber;
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

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
}
