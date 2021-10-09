package DBDAO;

import Beans.Category;
import Beans.Company;
import Beans.Coupon;
import DAO.CompaniesDAO;
import DB.ConnectionPool;
import DB.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Companies DBDAO class
 * exacute companies DAO
 */
public class CompaniesDBDAO implements CompaniesDAO {
    //Fields- Prepared statements
    /**
     * Field check if exists- perpared statement for checking if the company exists already in sql
     */
    private static final String CHECK_IF_EXIST = "SELECT * FROM `luxuryCoupons`.`companies` WHERE (`email` = ?) AND (`password` = ?)";
    /**
     * Field add company- is a prepared statement for adding a comapny to the sql table
     */
    private static final String ADD_COMPANY = "INSERT INTO `luxuryCoupons`.`companies` (`name`,`email`,`password`) VALUES (?,?,?)";
    /**
     * Field update company- is a prepared statement for updating company details on sql table
     */
    private static final String UPDATE_COMPANY = "UPDATE `luxuryCoupons`.`companies` SET email=?, password=? WHERE id=?";
    /**
     * Field delete company- is a prepared statment for deleting a company from sql
     */
    private static final String DELETE_COMPANY = "DELETE FROM `luxuryCoupons`.`companies` WHERE (`id` = ?);";
    /**
     * Field get one company- is a prepared statement for getting a company from sql to the program
     */
    private static final String GET_ONE_COMPANY = "SELECT * FROM `luxuryCoupons`.`companies` WHERE (`id` = ?);";
    /**
     * Field get all companies- is a prepared statement for getting all the companies from sql to the program
     */
    private static final String GET_ALL_COMPANIES = "SELECT * FROM `luxuryCoupons`.`companies`";
    /**
     * Field get all companies coupons- is a prepared statement for getting all the ocupons of a company from sql to the program
     */
    private static final String GET_ALL_COMPANY_COUPONS = "SELECT coupons.id, coupons.companyId, coupons.amount, coupons.price, coupons.categoryId, coupons.title, coupons.description, coupons.image, coupons.startDate, coupons.endDate FROM `luxuryCoupons`.`coupons` INNER JOIN `luxuryCoupons`.`companies` ON companies.id = coupons.companyId WHERE companies.id=?";

    /**
     * Constructor
     * gets instance for connectionPool
     */
    //Constructor
    public CompaniesDBDAO() {
        ConnectionPool.getInstance();
    }

    /**
     * Is Company Exist
     * checks if the company exists in database in sql table by the params:
     *
     * @param email     - company's email
     * @param password- company's password
     * @return boolean- returns if the company exists in the table
     */
    @Override
    public boolean isCompanyExist(String email, String password) {
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
            System.out.println("CompaniesDBDAO -'Is company exist' method" + ":\n" + err.getMessage()+"\n");
        }
        return false;
    }

    /**
     * Add company
     * adds object of company to sql table
     *
     * @param company - the company object we want to add to the sql table
     */
    @Override
    public void addCompany(Company company) {
        try {
            //create an empty map, Int as key, object as value
            Map<Integer, Object> params = new HashMap<>();
            //our params....
            params.put(1, company.getName());
            params.put(2, company.getEmail());
            params.put(3, company.getPassword());
            //run the query
            DBUtils.runQuery(ADD_COMPANY, params);
        } catch (SQLException err) {
            System.out.println("CompaniesDBDAO -'Add company' method"+ ":\n" + err.getMessage()+"\n");
        }
    }

    /**
     * Update company
     * Updates the values of existing company in the sql table
     *
     * @param company - gets the company object with the new values to update
     */
    @Override
    public void updateCompany(Company company) {
        try {
            //create an empty map, Int as key, object as value
            Map<Integer, Object> params = new HashMap<>();
            //our params....
            params.put(1, company.getEmail());
            params.put(2, company.getPassword());
            params.put(3, company.getId());
            //run the query
            DBUtils.runQuery(UPDATE_COMPANY, params);
        } catch (SQLException err) {
            System.out.println("CompaniesDBDAO -'Update company' method" + ":\n" + err.getMessage()+"\n");
        }
    }

    /**
     * Delete company
     * deletes a company from sql table
     *
     * @param companyId - get the company's id as identifier
     * @return boolean - if the ompany was eleted successfully
     */
    @Override
    public boolean deleteCompany(int companyId) {
        try {
            //create an empty map, Int as key, object as value
            Map<Integer, Object> params = new HashMap<>();
            //our params....
            params.put(1, companyId);
            //run the query
            DBUtils.runQuery(DELETE_COMPANY, params);
            return true;
        } catch (SQLException err) {
            System.out.println("CompaniesDBDAO -'Delete company' method"  + ":\n" + err.getMessage()+"\n");
            return false;
        }
    }

    /**
     * Get all companies
     * gets all the companies from sql
     *
     * @return list of all the companies in DB
     */
    @Override
    public List<Company> getAllCompanies() {
        //Create return list
        List<Company> companiesList = new ArrayList<>();
        Map<Integer, Object> params = new HashMap<>();
        try {
            ResultSet resultSet = DBUtils.getQuery(GET_ALL_COMPANIES, params);
            while (resultSet.next()) {
                Company company = new Company(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
                companiesList.add(company);
            }
            return companiesList;
        } catch (SQLException err) {
            System.out.println("CompaniesDBDAO -'Get all companies' method"+ ":\n" + err.getMessage()+"\n");
        }
        return companiesList;
    }

    /**
     * Get one company
     * gets a company from sql
     *
     * @param companyId - gets a company by id as identifier
     * @return Company - return a single company from DB
     */
    @Override
    public Company getOneCompany(int companyId) {
        //`id` = ? -> companyId
        Company result = null;
        Map<Integer, Object> params = new HashMap<>();
        try {
            params.put(1, companyId);
            //Get result from database
            ResultSet resultSet = DBUtils.getQuery(GET_ONE_COMPANY, params);
            resultSet.next();
            //Enter the return result --> String name, String email, String password
            result = new Company(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4));
            //Sets the coupons of the company to the result object
            result.setCoupons(getAllCouponsByCompany(companyId));
        } catch (SQLException err) {
            System.out.println("CompaniesDBDAO -'Get one company' method" + ":\n" + err.getMessage()+"\n");
        }
        return result;
    }

    /**
     * Get all coupons by company
     * this method returns a list of all the coupons of one company
     *
     * @param companyId- get the company's id as identifier
     * @return list of all the company coupons
     */
    public List<Coupon> getAllCouponsByCompany(int companyId) {
        //Create return list
        List<Coupon> couponsList = new ArrayList<>();
        Map<Integer, Object> params = new HashMap<>();
        try {
            params.put(1, companyId);
            ResultSet resultSet = DBUtils.getQuery(GET_ALL_COMPANY_COUPONS, params);
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
            System.out.println("CompaniesDBDAO -'Get all coupons by company' method"+ ":\n" + err.getMessage()+"\n");
        }
        return couponsList;
    }
}
