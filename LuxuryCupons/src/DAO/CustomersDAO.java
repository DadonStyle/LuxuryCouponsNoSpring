package DAO;

import Beans.Coupon;
import Beans.Customer;
import Exceptions.LuxuryCouponsException;

import java.util.List;

/**
 * The Customers DAO interface
 * indicates which methods we will implement in the customers DBDAO class
 */
public interface CustomersDAO {

    /**
     * Is customer exist
     * To check if the customer exists in sql table by 2 params:
     * @param email - the customer's email
     * @param password - the customer's password
     * @return boolean - returns if the customer exits in DB
     * @throws LuxuryCouponsException - if the customer doesn't exist
     */
    boolean isCustomerExist(String email, String password) throws LuxuryCouponsException;

    //CRUD - Create, Read, Update, Delete
    /**
     * Add customer
     * this method will create and add a customer to sql table
     * @param customer - creates a customer in DB by the customer param it gets
     */
    //Create
    void addCustomer(Customer customer);

    /**
     * Update customer
     * this method will update the values of a customer in sql table
     * @param customer - gets the customer with the new values for updating it on sql
     */
    //Update
    void updateCustomer(Customer customer);

    /**
     * Delete customer
     * this method will delete a customer from sql table
     * @param customerId - get the customer's id for deleting it from DB
     * @return boolean- if the customer was deleted
     */
    //Delete
    boolean deleteCustomer(int customerId);

    /**
     * Get all customers
     * this method gets the list of all the customers in the sql table
     * @return - returns a list of the customers exists in DB
     */
    //Read
    List<Customer> getAllCustomers();

    /**
     * Get one customer
     * this method gets a customer from the sql table by:
     * @param customerId - the customer's id as identifier
     * @return Company - returns a customer from DB
     */
    //Read
    Customer getOneCustomer(int customerId);

    /**
     * Get all coupons by customer
     * this method will get all the coupons of a specific customer
     * @param customerId - gets a customer id as identifier
     * @return - returns a list of all the coupons of a customer
     */
    List<Coupon> getAllCouponsByCustomer(int customerId);
}
