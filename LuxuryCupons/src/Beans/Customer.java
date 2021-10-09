package Beans;

import java.util.ArrayList;
import java.util.List;

/**
 * The Customer class
 * represent the customer information managed by the application
 */
public class Customer {
    //Fields
    /**
     * id field
     */
    private int id;
    /**
     * firstName, lastName, email, password - fields
     */
    private String firstName, lastName, email, password;
    /**
     * list of coupons field
     */
    private List<Coupon> coupons;

    /**
     * Default Customer constructor(Beans)
     */
    @SuppressWarnings("unused")
    public Customer() {
    }

    /**
     * Program Customer constructor for creating a customer object
     *
     * @param firstName - The customer's first name
     * @param lastName  - The customer's last name
     * @param email     - The customer's email
     * @param password  - The customer's password
     */
    public Customer(String firstName, String lastName, String email, String password) {
        this(0, firstName, lastName, email, password);
    }

    /**
     * Customer constructor for creating a customer object from sql(DB)
     *
     * @param id        - The sql auto increment customer's id in DB table
     * @param firstName - The customer's first name
     * @param lastName  - The customer's last name
     * @param email     - The customer's email
     * @param password  - The customer's password
     */
    public Customer(int id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        coupons = new ArrayList<>();
    }

    //Getters & Setters

    /**
     * Getter method
     *
     * @return Int - returns the customer's id on sql-DB table
     */
    public int getId() {
        return id;
    }


    /**
     * Getter method
     *
     * @return String - returns the customer's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter method
     *
     * @param firstName - sets the customer's first name by the param it gets
     */
    @SuppressWarnings("unused")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter method
     *
     * @return String - returns the customer's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter method
     *
     * @param lastName - sets the customer's last name by the param it gets
     */
    @SuppressWarnings("unused")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter method
     *
     * @return String - returns the customer's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method
     *
     * @param email - sets the customer's email by the param it gets
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter method
     *
     * @return String - returns the customer's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method
     *
     * @param password - sets the customer's password by the param it gets
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter method
     *
     * @return  the customer's purchased coupons list
     */
    public List<Coupon> getCoupons() {
        return coupons;
    }

    /**
     * Setter method
     *
     * @param coupons - sets the customer's purchased coupons list by the param it gets
     */
    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    /**
     * To string method
     *
     * @return String - returns the customer details for display
     */
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                "| firstName='" + firstName + '\'' +
                "| lastName='" + lastName + '\'' +
                "| email='" + email + '\'' +
                "| password='" + password + '\'' +
                "| coupons=" + coupons +
                "}\n";
    }
}
