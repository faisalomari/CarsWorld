package com.example.hello_hibernate;

import org.hibernate.Session;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;

    @Column(name = "First_Name")
    private String first_name;

    @Column(name = "Last_Name")
    private String last_name;

    @Column(name = "Password")
    private int password;

    @Column(name = "mail_address")
    private String mail;

    @Transient
    List<Car> Cars;

    @Column(name = "owned_cars")
    private String ownedCars;

    public Person(String First, String Last, int pass, String mail) {
        super();
        this.first_name = First;
        this.last_name = Last;
        this.password = pass;
        this.mail = mail;
    }

    public int getID() {return num;}
    public String getFirst_name() {
        return first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public int getPassword() {
        return password;
    }
    public String getMail() {
        return mail;
    }
    public List<Car> getCars() {
        return Cars;
    }
    public void setFirst_name(String f) {this.first_name = f;}
    public void setLast_name(String l) {
        this.last_name = l;
    }
    public void setPassword(int pass) {
        this.password = pass;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public void setCars(List<Car> cars) {
        StringBuilder carNames = new StringBuilder();
        for (Car car : cars) {
            car.setOwnerID(this.num);
            carNames.append(car.getId()).append(" ");
        }
        this.ownedCars = carNames.toString();
        this.Cars = cars;
    }

    public void AddCar(Car car) {
        car.setOwnerID(this.num);
        this.Cars.add(car);
        this.ownedCars += car.getId() + ",";
    }

    public List<Car> GetCars(){return this.Cars;}


}
