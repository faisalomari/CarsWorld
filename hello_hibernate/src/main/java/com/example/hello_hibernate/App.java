package com.example.hello_hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class App {
    private static Session session;

    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        // Add ALL of your entities here. You can also try adding a whole package.
        configuration.addAnnotatedClass(Garage.class);
        configuration.addAnnotatedClass(Image.class);
        configuration.addAnnotatedClass(Car.class);
        configuration.addAnnotatedClass(Person.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private static List<Car> generateCars(int num) throws Exception {
        Random random = new Random();
        List<Car> Cars = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Car car = new Car("MOO-" + random.nextInt(), 100000, 2000 + random.nextInt(19));
            Cars.add(car);
            session.save(car);
            /*
            * The call to session.flush() updates the DB immediately without ending the transaction.
            * Recommended to do after an arbitrary unit of work.
            * MANDATORY to do if you are saving a large amount of data - otherwise you may get cache errors.
            */
            session.flush();
        }
        return Cars;
    }

    private static List<Car> getAllCars() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Car> query = builder.createQuery(Car.class);
        query.from(Car.class);
        List<Car> data = session.createQuery(query).getResultList();
        return data;
    }

    private static List<Person> getAllPersons() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Person> query = builder.createQuery(Person.class);
        query.from(Person.class);
        List<Person> data = session.createQuery(query).getResultList();
        return data;
    }

    private static List<Image> getAllImages() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Image> query = builder.createQuery(Image.class);
        query.from(Image.class);
        List<Image> data = session.createQuery(query).getResultList();
        return data;
    }

    private static List<Garage> getAllGarages() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Garage> query = builder.createQuery(Garage.class);
        query.from(Garage.class);
        List<Garage> data = session.createQuery(query).getResultList();
        return data;
    }
    private static void printAllCars() throws Exception {
        List<Car> cars = getAllCars();
        for (Car car : cars) {
            System.out.print("Id: ");
            System.out.print(car.getId());
            System.out.print(", Owner Id: ");
            System.out.print(car.getOwnerID());
            System.out.print(", License plate: ");
            System.out.print(car.getLicensePlate());
            System.out.print(", Price: ");
            System.out.print(car.getPrice());
            System.out.print(", Year: ");
            System.out.print(car.getYear());
            System.out.print('\n');
        }
    }
    public static void save_all(Session session) throws Exception {
        for(Car car : getAllCars()){
            session.save(car);
            session.flush();
        }
        for(Person p : getAllPersons()){
            session.save(p);
            session.flush();
        }
        for(Image im: getAllImages()){
            session.save(im);
            session.flush();
        }
        for(Garage g: getAllGarages()){
            session.save(g);
            session.flush();
        }
    }
    public static Person generateNewPerson(String first, String last, int pass, String mail){
        Person p = new Person(first, last, pass, mail);
        session.save(p);
        session.flush();
        return p;
    }

    public static Image generateNewImage(){
        Random random = new Random();
        Image im = new Image("Image-" + Integer.toString(random.nextInt()));
        session.save(im);
        session.flush();
        return im;
    }

    public static Garage generateNewGarage(String add, String phone, List<Person> persons, List<Car> cars){
        Garage garage = new Garage(add, phone, persons, cars);
        session.save(garage);
        session.flush();
        return garage;
    }

    public static void CreateData() throws Exception {
        Person p1 = generateNewPerson("Faisal", "Omari", 1234, "faisalomari321@gmail.com");
        Person p2 = generateNewPerson("Daniel", "Luka", 1235, "DanielLuka@gmail.com");
        Person p3 = generateNewPerson("John", "Cena", 1236, "JohnCena@gmail.com");
        Person p4 = generateNewPerson("Lucas", "Hernandez", 1237, "LucasHernandez@gmail.com");
        Person p5 = generateNewPerson("David", "Lieber", 1239, "DavidLieber@gmail.com");
        Person p6 = generateNewPerson("Lionel", "Messi", 1238, "LionelMessi@gmail.com");
        Person p7 = generateNewPerson("Cristiano", "Ronaldo", 1213, "CristianoRonaldo@gmail.com");
        Person p8 = generateNewPerson("Karim", "Benzema", 4321, "KarimBenzema@gmail.com");
        Person p9 = generateNewPerson("Mohammad", "Jad", 4223, "MohammadJad@gmail.com");
        Person p10 = generateNewPerson("Ali", "Ghalb", 4232, "AliGhalb@gmail.com");
        save_all(session);
        p1.setCars(generateCars(2));
        p2.setCars(generateCars(1));
        p3.setCars(generateCars(1));
        p4.setCars(generateCars(3));
        p5.setCars(generateCars(1));
        p6.setCars(generateCars(1));
        p7.setCars(generateCars(1));
        p8.setCars(generateCars(2));
        p9.setCars(generateCars(1));
        p10.setCars(generateCars(1));
        save_all(session);
        for(Car car: getAllCars()){
            car.setImageOfCar(generateNewImage());
        }
        save_all(session);
        List<Person> Owners_group1 = new ArrayList<>();
        List<Person> Owners_group2 = new ArrayList<>();
        List<Person> Owners_group3 = new ArrayList<>();
        Owners_group1.add(p1);
        Owners_group2.add(p4);
        Owners_group2.add(p5);
        Owners_group3.add(p7);
        Owners_group3.add(p9);
        Owners_group3.add(p10);
        save_all(session);
        List<Car> Cars_group1 = getAllCars().subList(0,3);
        List<Car> Cars_group2 = getAllCars().subList(3,9);
        List<Car> Cars_group3 = getAllCars().subList(9,14);
        Garage garage1 = generateNewGarage("Hanamal_16", "04-9927718", Owners_group1, Cars_group1);
        Garage garage2 = generateNewGarage("Tel-Aviv, Azreili 96st.", "03-7839283", Owners_group2, Cars_group2);
        Garage garage3 = generateNewGarage("Jursalem, Wadi-AlJuz st.43", "03-1827472", Owners_group3, Cars_group3);
        save_all(session);
    }


    public static void printHW(){

    }

    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            CreateData();
            printAllCars();
            session.getTransaction().commit(); // Save everything.
        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            exception.printStackTrace();
        } finally {
            session.close();
        }
    }
}