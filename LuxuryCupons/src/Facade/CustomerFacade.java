package Facade;

import Beans.Category;
import Beans.Coupon;
import Beans.Customer;
import DAO.CustomersDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDBDAO;
import Exceptions.LuxuryCouponsException;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * The Customer facade class
 * extends the clinet facade and indicates the methods that the customer can perform
 */
public class CustomerFacade extends ClientFacade {
    //Fields
    /**
     * customer id field- the customer's id
     */
    private int customerID;
    /**
     * customers DAO field for using all it's methods
     */
    private final CustomersDAO customersDAO;
    /**
     * report name field is a final string that represnts the class name for the custom exception
     */
    private static final String REPORT_NAME = "Customer Facade";

    /**
     * Default constructor
     */
    public CustomerFacade() {
        this.customersDAO = new CustomersDBDAO();
    }

    /**
     * Constructor for making customer facade object
     *
     * @param customerID- get the customer's id
     */
    public CustomerFacade(int customerID) {
        this.customerID = customerID;
        this.customersDAO = new CustomersDBDAO();
    }

    //Getter

    /**
     * Get customer ID
     *
     * @return int - returns the customer id
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Login method
     * for the identification of the customer, so he can make actions on his account
     *
     * @param email    - the email of the client for login
     * @param password - the password of the client for login
     * @return boolean - returns if the login was successful
     * @throws LuxuryCouponsException - throws an error if the email and password eneterd were incorrect
     */
    @Override
    public boolean login(String email, String password) throws LuxuryCouponsException {
        if (customersDAO.isCustomerExist(email, password)) {
            System.out.println("Logged in successfully - Customer!"+"\n");
            return true;
        }
        throw new LuxuryCouponsException("Something went wrong ! \n Please check your email and password", REPORT_NAME);
    }

    /**
     * Make purchase
     * assistant method for making the purchase when meets all the conditions
     *
     * @param coupon-gets the coupon we would like to purchase
     */
    private void makePurchase(Coupon coupon) {
        CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
        //Adding a purchase to the sql table
        couponsDBDAO.addCouponPurchase(getCustomerID(), coupon.getId());
        //Updating the new amount after purchasing
        coupon.setAmount((coupon.getAmount() - 1));
        couponsDBDAO.updateCoupon(coupon);
        System.out.println("Coupon " + coupon.getTitle() + " purchased successfully !"+"\n");
    }

    /**
     * Purhase coupon
     * this method will chcek if the purchasing matches all the conditions, and makes the purchase if possible
     *
     * @param coupon - gets the coupon the customer wants to purchase
     * @throws LuxuryCouponsException - throws an error based on what condition wasn't right
     */
    public void purchaseCoupon(Coupon coupon) throws LuxuryCouponsException {
        List<Coupon> coupons = customersDAO.getAllCouponsByCustomer(getCustomerID());
        Date currentDate = new java.util.Date(new java.util.Date().getTime());
        //checks if the coupon expired
        if (currentDate.after(coupon.getEndDate()))
            throw new LuxuryCouponsException("Coupon purchase Unsuccessfull !\n Coupon expired", REPORT_NAME);
        //checks if the coupon is not in stock
        if (coupon.getAmount() < 0)
            throw new LuxuryCouponsException("Coupon purchase Unsuccessfull !\n Coupon not available", REPORT_NAME);
        if (coupons.size() > 0) {
            for (Coupon item : coupons) {
                //Checks if the customer already purchased this coupon
                if ((coupon.getId() == item.getId())) {
                    throw new LuxuryCouponsException("Coupon purchase Unsuccessfull !\n Can't purchase the same coupon more then once", REPORT_NAME);
                }
            }
        }
        //If past all the conditions and didn't fall in exception - makes the purchase
        makePurchase(coupon);
    }

    /**
     * Get customer coupons
     *
     * @return List of all the coupons of one customer
     */
    public List<Coupon> getCustomerCoupons() {
        return customersDAO.getAllCouponsByCustomer(this.customerID);
    }

    /**
     * Get customer coupons by category
     *
     * @param category - gets a specific category
     * @return List of all the coupons of one customer on specific category
     */
    public List<Coupon> getCustomerCoupons(Category category) {
        LinkedList<Coupon> coupons = new LinkedList<>(getCustomerCoupons());
        coupons.removeIf(coupon -> !(coupon.getCategory().equals(category)));
        return coupons;
    }

    /**
     * Get customer coupons under max price
     *
     * @param maxPrice - gets the max price for limitation
     * @return list of coupons of one customer under max price
     */
    public List<Coupon> getCustomerCoupons(double maxPrice) {
        LinkedList<Coupon> coupons = new LinkedList<>(getCustomerCoupons());
        coupons.removeIf(coupon -> coupon.getPrice() > maxPrice);
        return coupons;
    }

    /**
     * Get customer details
     *
     * @return Customer- returns a customer object for display
     */
    public Customer getCustomerDetails() {
        return customersDAO.getOneCustomer(this.customerID);
    }
}
