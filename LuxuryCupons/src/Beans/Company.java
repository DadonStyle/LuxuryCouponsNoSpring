package Beans;

import java.util.ArrayList;
import java.util.List;

/**
 * The Company class
 * represent the company information managed by the application
 */
public class Company {
    //Fields
    /**
     * id- field
     */
    private int id;
    /**
     * name, email, password - fields
     */
    private String name, email, password;
    /**
     * list of coupons field
     */
    private List<Coupon> coupons;

    /**
     * Default Company constructor (Beans)
     */
    @SuppressWarnings("unused")
    public Company() {
    }

    /**
     * Program Company constructor for creating a company object
     *
     * @param name     - The name of the company
     * @param email    - The company's email
     * @param password - The company's password
     */
    public Company(String name, String email, String password) {
        this(0, name, email, password);
    }

    /**
     * Company constructor - for creating a company object from sql (DB)
     * constructor for building company object when getting it from sql with id
     *
     * @param id       - The sql auto increment id in the DB table of the company
     * @param name     - The name of the company
     * @param email    - The company's email
     * @param password - The company password
     */
    public Company(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        coupons = new ArrayList<>();
    }

    //Getters & Setters
    /**
     * Getter method
     *
     * @return Int - returns the company's sql auto increment id in the DB table
     */
    public int getId() {
        return id;
    }

    /**
     * Getter method
     *
     * @return String - returns company name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method
     *
     * @param name - sets company name by the param it gets
     */
    @SuppressWarnings("unused")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method
     *
     * @return String - returns the company's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method
     *
     * @param email - sets the email by the param it gets
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter method
     *
     * @return String - returns the company's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method
     *
     * @param password - sets the password by the param it gets
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter method
     *
     * @return - list of coupons of the company
     */
    public List<Coupon> getCoupons() {
        return coupons;
    }

    /**
     * Setter method
     *
     * @param coupons - sets a list of coupons by the param it gets
     */
    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    /**
     * To string method
     *
     * @return String - returns all the company info for display
     */
    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                "| name='" + name + '\'' +
                "| email='" + email + '\'' +
                "| password='" + password + '\'' +
                "| coupons=" + coupons + "}\n";
    }
}
