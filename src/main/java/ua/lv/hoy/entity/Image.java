package ua.lv.hoy.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Administrator on 31-Mar-17.
 */
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private  int id;
    @Column
    private String imagePath;

    @ManyToMany
    @JoinTable(name = "flat_image",
            joinColumns = @JoinColumn(name = "imageId"),
            inverseJoinColumns = @JoinColumn(name = "flatId"))
    private List<Flat>flatList;


    public Image() {
    }

    public Image(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<Flat> getFlatList() {
        return flatList;
    }

    public void setFlatList(List<Flat> flatList) {
        this.flatList = flatList;
    }
}
