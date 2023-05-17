package com.example.hello_hibernate;


import org.hibernate.Session;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String licensePlate;

    private double price;

    @Column(name = "manufacturing_year")
    private int year;

    @Column(name = "Owner_id")
    private int OwnerID;

    @Transient
    private Image image;
    @Column(name = "Image_id")
    private String Image_id;

    @Column(name = "Certified_Garages")
    private String garages_s;
    @Transient
    private List<Integer> Garages_IDs;
    @Transient
    private List<Garage> Garages;
    public Car(String licensePlate, double price, int year) {
        super();
        this.licensePlate = licensePlate;
        this.price = price;
        this.year = year;
    }
    public String getLicensePlate() {
        return licensePlate;
    }
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getId() {
        return id;
    }
    public int getOwnerID(){return OwnerID;}
    public void setOwnerID(int num){
        this.OwnerID = num;
    }
    public void setOwner(Person p){
        p.AddCar(this);
    }
    public void setImage(Image im){
        this.image = im;
        this.Image_id = im.getImageID();
    }
    public void setImageOfCar(Image im){
        im.setCarOfIm(this);
    }
    public String getImage(){
        return this.Image_id;
    }
    public void SetGarages(List<Garage> g){
        this.Garages = g;
        for(Garage garage : g){
            this.Garages_IDs.add(garage.getGarageID());
        }
        this.garages_s = listToString(this.Garages_IDs);
    }
    public void AddGarage(Garage g){
        this.Garages.add(g);
        this.Garages_IDs.add(g.getGarageID());
        this.garages_s = listToString(this.Garages_IDs);
    }
    public List<Garage> getGarages(){return this.Garages;}
    public List<Integer> getGarages_IDs(){return this.Garages_IDs;}

    public static String listToString(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (Integer number : list) {
            sb.append(number).append(" ");
        }
        return sb.toString().trim();
    }
}