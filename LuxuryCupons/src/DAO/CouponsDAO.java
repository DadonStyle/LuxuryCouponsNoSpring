package DAO;

import Beans.Coupon;

import java.util.List;

/**
 * The Coupons DAO interface
 * indicates which methods we will implement in the coupons DBDAO class
 */
public interface CouponsDAO {

    //CRUD - Create, Read, Update, Delete
    /**
     * Add coupon
     * this method will create and add a new coupon to the sql table
     * @param coupon - creates a coupons in DB by the company param it gets
     */
    //Create
    void addCoupon(Coupon coupon);

    /**
     * Update coupon
     * this method will update the values of a coupon in sql table
     * @param coupon - gets the coupon with the new values for updating it on sql
     */
    //Update
    void updateCoupon(Coupon coupon);

    /**
     * Delete coupon
     * this method will delete a coupon from sql table
     * @param couponId - get the coupon's id for deleting it from DB
     * @return boolean- if the coupons was eleted successfully
     */
    //Delete
    boolean deleteCoupon(int couponId);

    /**
     * Get all coupons
     * this method gets the list of all the coupons in the sql table
     * @return - returns a list of the coupons exists in DB
     */
    //Read
    List<Coupon> getAllCoupons();

    /**
     * Get one coupon
     * this method gets a coupon from the sql table by:
     * @param couponId - the coupon's id as identifier
     * @return coupon - returns a coupon from DB
     */
    //Read
    Coupon getOneCoupon(int couponId);

    /**
     * Add coupon purchase
     * Creates and adds a coupon purchase to the sql table by the params:
     * @param customerId - the customer's id it gets
     * @param couponId - the coupon's id it gets
     */
    void addCouponPurchase(int customerId, int couponId);

    /**
     * Delete coupon purchase
     * deletes a coupon purchase form the sql table by the params:
     * @param customerId - the customer's id it gets
     * @param couponId - the coupon's id it gets
     */
    @SuppressWarnings("unused")
    void deleteCouponPurchase(int customerId, int couponId);

    /**
     * Delete coupon purchase by coupon id
     * deletes a purchase of a coupon by a coupon id from sql table
     * @param couponId -gets the coupon's id as identifier
     */
    void deleteCouponPurchaseByCouponId(int couponId);
}
