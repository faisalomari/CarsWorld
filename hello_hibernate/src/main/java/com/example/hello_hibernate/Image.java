package com.example.hello_hibernate;


import javax.persistence.*;

@Entity
@Table(name = "Images")
public class Image{
    @Id
    @Column(name = "Image_id")
    private String id;

    @Column(name = "Car_of_Im")
    private int car_id;
    public Image(String id) {
        super();
        this.id = id;
    }

    public String getImageID(){return this.id;}

    public void setCarOfIm(Car car) {
        this.car_id = car.getId();
        car.setImage(this);
    }

    public int getCarOfIm() {return this.car_id;}
}