package com.example.hello_hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Garages")
public class Garage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Address")
    private String address;

    @Column(name = "Phone_Number")
    private String phone;

    @Column(name = "Owners_IDs")
    private String owners_ids;
    @Transient
    private List<Integer> ownersIDs = new ArrayList<>();

    @Transient
    private List<Person> owners = new ArrayList<>();

    @Column(name = "Cars_IDs")
    private String cars_ids;
    @Transient
    private List<Integer> carsIDs = new ArrayList<>();

    @Transient
    private List<Car> cars = new ArrayList<>();

    public Garage(String add, String phone, List<Person> list, List<Car> cars) {
        super();
        this.address = add;
        this.phone = phone;
        this.owners = list;
        this.cars = cars;
        for (Person p : list){
            this.ownersIDs.add(p.getID());
        }
        for (Car car : cars){
            this.carsIDs.add(car.getId());
        }
        this.owners_ids = listToString(ownersIDs);
        this.cars_ids = listToString(carsIDs);
    }
    public void AddOwner(Person p){
        this.ownersIDs.add(p.getID());
        this.owners.add(p);
        this.owners_ids = listToString(this.ownersIDs);
    }
    public void AddCar(Car car){
        this.carsIDs.add(car.getId());
        this.cars.add(car);
        this.cars_ids = listToString(this.carsIDs);
    }

    public int getGarageID(){return this.id;}
    public String getAddress(){return this.address;}
    public String getPhone(){return this.phone;}
    public List<Integer> getOwnersIDs(){return this.ownersIDs;}
    public List<Person> getOwners(){return this.owners;}

    public List<Integer> getCarsIDs(){return this.carsIDs;}
    public List<Car> getCars(){return this.cars;}

    public static String listToString(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (Integer number : list) {
            sb.append(number).append(" ");
        }
        return sb.toString().trim();
    }
}
