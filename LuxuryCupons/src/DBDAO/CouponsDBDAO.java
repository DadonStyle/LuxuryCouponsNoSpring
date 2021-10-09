package DBDAO;

import Beans.Category;
import Beans.Coupon;
import DAO.CouponsDAO;
import DB.ConnectionPool;
import DB.DBUtils;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Coupons DBDAO class
 * exacute coupons DAO
 */
public class CouponsDBDAO implements CouponsDAO {
    //Fields - Prepared statements
    /**
     * Field add coupon- is a prepared statement for adding a coupon to the sql
     */
    private static final String ADD_COUPON = "INSERT INTO `luxuryCoupons`.`coupons` (`companyId`,`categoryId`, `title`,`description`, `startDate`, `endDate`, `amount`, `price`, `image`) VALUES (?,?,?,?,?,?,?,?,?)";
    /**
     * Field update coupon- is a prepared statement fot updating a coupon's details on sql
     */
    private static final String UPDATE_COUPON = "UPDATE `luxuryCoupons`.`coupons` SET categoryId=?, title=?, description=?, startDate=?, endDate=?, amount=?, price=?, image=? WHERE id=?";
    /**
     * Field delete coupon- is a prepared statement for deleting a coupon from sql
     */
    private static final String DELETE_COUPON = "DELETE FROM `luxuryCoupons`.`coupons` WHERE (`id` = ?)";
    /**
     * Field get one coupon- is a prepared statement for getting a coupon from sql to the program
     */
    private static final String GET_ONE_COUPON = "SELECT * FROM `luxuryCoupons`.`coupons` WHERE (`id` = ?)";
    /**
     * Field get all coupons- is a prepared statement for getting all the coupons from sql to the program
     */
    private static final String GET_ALL_COUPONS = "SELECT * FROM `luxuryCoupons`.`coupons`";
    /**
     * Field add coupon purchase- is a prepared statement for adding a coupon purchase to the sql
     */
    private static final String ADD_COUPON_PURCHASE = "INSERT INTO `luxuryCoupons`.`customers_vs_coupons` (`customerId`, `couponId`) VALUES (?,?)";
    /**
     * Field delete coupon purchase- is a prepared satetment for deleting a coupon purchase from sql
     */
    private static final String DELETE_COUPON_PURCHASE = "DELETE FROM `luxuryCoupons`.`customers_vs_coupons` WHERE `customerId`=?, `couponId`=?";
    /**
     * Field delete coupon purchase by coupon id- is a prepared statement for deleting a coupon purchase from sql by coupon id
     */
    private static final String DELETE_COUPON_PURCHASE_BY_COUPON_ID = "DELETE FROM `luxuryCoupons`.`customers_vs_coupons` WHERE `couponId`=?";

    /**
     * Constructor
     * gets ConnectionPool instance
     */
    public CouponsDBDAO() {
        ConnectionPool.getInstance();
    }

    /**
     * Add coupon
     * adds object of coupon to sql table
     *
     * @param coupon - the coupon object we want to add to the sql table
     */
    public void addCoupon(Coupon coupon) {
        //`companyId`,`categoryId`, `title`,`description`, `startDate`, `endDate`, `amount`, `price`, `image`
        try {
            //create an empty map, Int as key, object as value
            Map<Integer, Object> params = new HashMap<>();
            //our params....
            params.put(1, coupon.getCompanyId());
            params.put(2, coupon.getCategoryId());
            params.put(3, coupon.getTitle());
            params.put(4, coupon.getDescription());
            params.put(5, coupon.getStartDate());
            params.put(6, coupon.getEndDate());
            params.put(7, coupon.getAmount());
            params.put(8, coupon.getPrice());
            params.put(9, coupon.getImage());
            //run the query
            DBUtils.runQuery(ADD_COUPON, params);
        } catch (SQLException err) {
            System.out.println("CouponsDBDAO -'Add coupon' method"  + ":\n" + err.getMessage()+"\n");
        }
    }

    /**
     * Update coupon
     * Updates the values of existing company in the sql table
     *
     * @param coupon - gets the coupon object with the new values to update
     */
    public void updateCoupon(Coupon coupon) {
        try {
            //create an empty map, Int as key, object as value
            Map<Integer, Object> params = new HashMap<>();
            //our params....
            params.put(1, coupon.getCategoryId());
            params.put(2, coupon.getTitle());
            params.put(3, coupon.getDescription());
            params.put(4, coupon.getStartDate());
            params.put(5, coupon.getEndDate());
            params.put(6, coupon.getAmount());
            params.put(7, coupon.getPrice());
            params.put(8, coupon.getImage());
            params.put(9, coupon.getId());
            //run the query
            DBUtils.runQuery(UPDATE_COUPON, params);
        } catch (SQLException err) {
            System.out.println("CouponsDBDAO -'Update coupon' method"  + ":\n" + err.getMessage()+"\n");
        }
    }

    /**
     * Delete coupon
     * deletes a company from sql table
     *
     * @param couponId - get the coupon's id as identifier
     * @return boolean - if the coupon was deleted successfully
     */
    public boolean deleteCoupon(int couponId) {
        //`id` = ? -> couponId
        try {
            //create an empty map, Int as key, object as value
            Map<Integer, Object> params = new HashMap<>();
            //our params....
            params.put(1, couponId);
            //run the query
            DBUtils.runQuery(DELETE_COUPON, params);
        } catch (SQLException err) {
            System.out.println("CouponsDBDAO -'Delete coupon' method"  + ":\n" + err.getMessage()+"\n");
            return false;
        }
        return true;
    }

    /**
     * Get all coupons
     * gets all the coupons from sql
     *
     * @return list of all the coupons in DB
     */
    public List<Coupon> getAllCoupons() {
        //Create the result coupons list
        List<Coupon> couponsList = new ArrayList<>();
        Map<Integer, Object> params = new HashMap<>();
        try {
            //Save result
            ResultSet resultSet = DBUtils.getQuery(GET_ALL_COUPONS, params);
            while (resultSet.next()) {
                //int id, int companyId, int amount, double price, Category category, String title, String description, String image, Date startDate, Date endDate
                Coupon coupon = new Coupon(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getDouble(4),
                        Category.getCategoryById(resultSet.getInt(5)),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getDate(9),
                        resultSet.getDate(10));
                couponsList.add(coupon);
            }
            return couponsList;
        } catch (SQLException err) {
            System.out.println("CouponsDBDAO -'Get all coupons' method" + ":\n" + err.getMessage()+"\n");
        }
        return couponsList;
    }

    /**
     * Get one coupon
     * gets a company from sql
     *
     * @param couponId - gets a coupon by id as identifier
     * @return Coupon - return a single coupon from DB
     */
    public Coupon getOneCoupon(int couponId) {
        //`id` = ? -> couponId
        Coupon result = null;
        Map<Integer, Object> params = new HashMap<>();
        try {
            params.put(1, couponId);
            //Get result from database
            ResultSet resultSet = DBUtils.getQuery(GET_ONE_COUPON, params);
            resultSet.next();
            //Inset the result values-->int id, int companyId, int amount, double price, Category category, String title, String description, String image, Date startDate, Date endDate
            result = new Coupon(resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4),
                    Category.getCategoryById(resultSet.getInt(5)),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getDate(9),
                    resultSet.getDate(10));
        } catch (SQLException err) {
            System.out.println("CouponsDBDAO -'Get one coupon' method"  + ":\n" + err.getMessage()+"\n");
        }
        return result;
    }

    /**
     * Add coupon purchase
     * adds a purchase of a coupon by a customer to sql table
     *
     * @param customerId - gets the customer's id
     * @param couponId   - gets the coupon's id
     */
    public void addCouponPurchase(int customerId, int couponId) {
        //create an empty map, Int as key, object as value
        Map<Integer, Object> params = new HashMap<>();
        try {
            //our params....
            params.put(1, customerId);
            params.put(2, couponId);
            //run the query
            DBUtils.runQuery(ADD_COUPON_PURCHASE, params);
        } catch (SQLException err) {
            System.out.println("CouponsDBDAO -'Add coupon purchase' method"  + ":\n" + err.getMessage()+"\n");
        }
    }

    /**
     * Delete coupon purchase
     * deletes a purchase of a coupon by a customer from sql table
     *
     * @param customerId - gets the customer's id
     * @param couponId   - gets the coupon's id
     */
    @Override
    public void deleteCouponPurchase(int customerId, int couponId) {
        try {
            //create an empty map, Int as key, object as value
            Map<Integer, Object> params = new HashMap<>();
            //our params....
            params.put(1, customerId);
            params.put(2, couponId);
            //run the query
            DBUtils.runQuery(DELETE_COUPON_PURCHASE, params);
        } catch (SQLException err) {
            System.out.println("CouponsDBDAO -'Delete coupon purchase' method"  + ":\n" + err.getMessage()+"\n");
        }
    }

    /**
     * Delete coupon purchase by coupon id
     * deletes a purchase of a coupon by a coupon id from sql table
     *
     * @param couponId -gets the coupon's id as identifier
     */
    @Override
    public void deleteCouponPurchaseByCouponId(int couponId) {
        try {
            //create an empty map, Int as key, object as value
            Map<Integer, Object> params = new HashMap<>();
            //our params....
            params.put(1, couponId);
            //run the query
            DBUtils.runQuery(DELETE_COUPON_PURCHASE_BY_COUPON_ID, params);
        } catch (SQLException err) {
            System.out.println("CouponsDBDAO -'Delete coupon purchase by id' method" + ":\n" + err.getMessage()+"\n");
        }
    }
}
