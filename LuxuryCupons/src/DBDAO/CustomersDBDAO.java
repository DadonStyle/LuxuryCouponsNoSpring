package DBDAO;

import Beans.Category;
import Beans.Coupon;
import Beans.Customer;
import DAO.CustomersDAO;
import DB.ConnectionPool;
import DB.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Coustomers DBDAO class
 * exacute customers DAO
 */
public class CustomersDBDAO implements CustomersDAO {
    //Fields- Prepared statement
    /**
     * Field check if exist- is a prepared statement for checking if the customer is already exist
     */
    private static final String CHECK_IF_EXIST = "SELECT * FROM `luxuryCoupons`.`customers` WHERE `customers`.`email` LIKE ? AND `customers`.`password` LIKE ?";
    /**
     * Field add customer- is a prepared statement for adding a customer to sql
     */
    private static final String ADD_CUSTOMER = "INSERT INTO `luxuryCoupons`.`customers` (`firstName`,`lastName`, `email`,`password`) VALUES (?,?,?,?)";
    /**
     * Field update customer- is a prepared statement for upaing the customer's details
     */
    private static final String UPDATE_CUSTOMER = "UPDATE `luxuryCoupons`.`customers` SET firstName=?, lastName=?, email=?, password=? WHERE id=?";
    /**
     * Field delete customer- is a prepared statement for deleting a customer from sql
     */
    private static final String DELETE_CUSTOMER = "DELETE FROM `luxuryCoupons`.`customers` WHERE (`id` = ?)";
    /**
     * Field get one customer- is a prepared statement for getting a customer from sql to the program
     */
    private static final String GET_ONE_CUSTOMER = "SELECT * FROM `luxuryCoupons`.`customers` WHERE (`id` = ?)";
    /**
     * Field get all customers- is a prepared statement for getting all the customers from sql to the program
     */
    private static final String GET_ALL_CUSTOMERS = "SELECT * FROM `luxuryCoupons`.`customers`";
    /**
     * Field get all coupons by customer id- is a prepared statement for getting all the coupons of a customer by his id from sql to the program
     */
    private static final String GET_ALL_COUPONS_BY_CUSTOMER = "SELECT coupons.id, coupons.companyId, coupons.amount, coupons.price, coupons.categoryId, coupons.title, coupons.description, coupons.image, coupons.startDate, coupons.endDate FROM `luxuryCoupons`.`coupons` INNER JOIN `luxuryCoupons`.`customers_vs_coupons` ON coupons.id = customers_vs_coupons.couponId WHERE customers_vs_coupons.customerId=?";

    /**
     * Constructor
     * gets ConnectionPool instance
     */
    public CustomersDBDAO() {
        ConnectionPool.getInstance();
    }

    /**
     * Is customer exist
     * checks if the customer exists in database in sql table by the params:
     *
     * @param email    - customer's email
     * @param password - customer's password
     * @return boolean - returns if the customer exists in the table
     */
    @Override
    public boolean isCustomerExist(String email, String password) {
        try {
            Map<Integer, Object> params = new HashMap<>();
            //our params....
            params.put(1, email);
            params.put(2, password);
            //run the query
            ResultSet resultSet = DBUtils.getQuery(CHECK_IF_EXIST, params);
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException err) {
            System.out.println("CutomersDBDAO -'Is ustomer exist' method" + ":\n" + err.getMessage()+"\n");
        }
        return false;
    }

    /**
     * Add customer
     * adds object of customer to sql table
     *
     * @param customer - the customer object we want to add to the sql table
     */
    @Override
    public void addCustomer(Customer customer) {
        //`firstName`,`lastName`, `email`,`password`
        try {
            //create an empty map, Int as key, object as value
            Map<Integer, Object> params = new HashMap<>();
            //our params....
            params.put(1, customer.getFirstName());
            params.put(2, customer.getLastName());
            params.put(3, customer.getEmail());
            params.put(4, customer.getPassword());
            //run the query
            DBUtils.runQuery(ADD_CUSTOMER, params);
        } catch (SQLException err) {
            System.out.println("CutomersDBDAO -'Add customer ' method" + ":\n" + err.getMessage()+"\n");
        }
    }

    /**
     * Update customer
     * Updates the values of existing customer in the sql table
     *
     * @param customer - gets the customer object with the new values to update
     */
    @Override
    public void updateCustomer(Customer customer) {
        //firstName=?, lastName=?, email=? , password=?
        try {
            //create an empty map, Int as key, object as value
            Map<Integer, Object> params = new HashMap<>();
            //our params....
            params.put(1, customer.getFirstName());
            params.put(2, customer.getLastName());
            params.put(3, customer.getEmail());
            params.put(4, customer.getPassword());
            params.put(5, customer.getId());
            //run the query
            DBUtils.runQuery(UPDATE_CUSTOMER, params);
        } catch (SQLException err) {
            System.out.println("CutomersDBDAO -'Update customer' method" +  ":\n" + err.getMessage()+"\n");
        }
    }

    /**
     * Delete customer
     * deletes a customer from sql table
     *
     * @param customerId - get the customer's id as identifier
     * @return boolean- if the customer was deleted
     */
    @Override
    public boolean deleteCustomer(int customerId) {
        //`id` = ? -> customerId
        try {
            //create an empty map, Int as key, object as value
            Map<Integer, Object> params = new HashMap<>();
            //our params....
            params.put(1, customerId);
            //run the query
            DBUtils.runQuery(DELETE_CUSTOMER, params);
            return true;
        } catch (SQLException err) {
            System.out.println("CutomersDBDAO -'Delete customer' method" + ":\n" + err.getMessage()+"\n");
            return false;
        }
    }

    /**
     * Get all customers
     * gets all the customers from sql
     *
     * @return list of all the customers in DB
     */
    @Override
    public List<Customer> getAllCustomers() {
        //Initialize the return customers list
        List<Customer> customersList = new ArrayList<>();
        Map<Integer, Object> params = new HashMap<>();
        try {
            //Save result
            ResultSet resultSet = DBUtils.getQuery(GET_ALL_CUSTOMERS, params);
            while (resultSet.next()) {
                //Makes a customer and adds it to the list -->int id, String firstName, String lastName, String email, String password
                Customer customer = new Customer(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5));
                customersList.add(customer);
            }
            return customersList;
        } catch (SQLException err) {
            System.out.println("CutomersDBDAO -'Get all customers' method" + ":\n" + err.getMessage()+"\n");
        }
        return customersList;
    }

    /**
     * Get one customer
     * gets a customer from sql
     *
     * @param customerId - gets a customer by id as identifier
     * @return Customer - return a single customer from DB
     */
    @Override
    public Customer getOneCustomer(int customerId) {
        //`id` = ? -> customerId
        //initialize the return result- customer
        Customer result = null;
        Map<Integer, Object> params = new HashMap<>();
        try {
            params.put(1, customerId);
            //Get result from database
            ResultSet resultSet = DBUtils.getQuery(GET_ONE_CUSTOMER, params);
            resultSet.next();
            //Make a customer -->String firstName, String lastName, String email, String password
            result = new Customer(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5));
            result.setCoupons(getAllCouponsByCustomer(customerId));
        } catch (SQLException err) {
            System.out.println("CutomersDBDAO -'Get oe customer' method" + ":\n" + err.getMessage()+"\n");
        }
        return result;
    }

    /**
     * Get all coupons by customer
     * this method returns a list of all the coupons of one customer
     *
     * @param customerId- get the customer's id as identifier
     * @return list of all the customer coupons
     */
    @Override
    public List<Coupon> getAllCouponsByCustomer(int customerId) {
        //Create return list
        List<Coupon> couponsList = new ArrayList<>();
        Map<Integer, Object> params = new HashMap<>();
        try {
            params.put(1, customerId);
            ResultSet resultSet = DBUtils.getQuery(GET_ALL_COUPONS_BY_CUSTOMER, params);
            //Enter each company to the list
            while (resultSet.next()) {
                Coupon coupon = new Coupon(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getDouble(4),
                        Category.getCategoryById(resultSet.getInt(5)),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getDate(9),
                        resultSet.getDate(10)
                );
                couponsList.add(coupon);
            }
        } catch (SQLException err) {
            System.out.println("CutomersDBDAO -'Get all coupons by customer' method" + ":\n" + err.getMessage()+"\n");
        }
        return couponsList;
    }
}
