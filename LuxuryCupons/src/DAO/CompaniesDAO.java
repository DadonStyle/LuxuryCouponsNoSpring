package DAO;

import Beans.Company;
import Beans.Coupon;

import java.util.List;

/**
 * The Company DAO interface
 * indicates which methods we will implement in the company DBDAO class
 */
public interface CompaniesDAO {
    /**
     * Is company exist
     * To check if the company exists in sql table by 2 params:
     *
     * @param email    - the company's email
     * @param password - the company's password
     * @return boolean - returns if the company exits in DB
     */
    boolean isCompanyExist(String email, String password);

    //CRUD - Create, Read, Update, Delete

    /**
     * Add company
     * this method will create and add a company to sql table
     *
     * @param company - creates a company in DB by the company param it gets
     */
    //Create
    void addCompany(Company company);

    /**
     * Update company
     * this method will update the values of a company in sql table
     *
     * @param company - gets the company with the new values for updating it on sql
     */
    //Update
    void updateCompany(Company company);

    /**
     * Delete company
     * this method will delete a company from sql table
     *
     * @param companyId - get the company's id for deleting it from DB
     * @return boolean - if the company was deleted successfully
     */
    //Delete
    boolean deleteCompany(int companyId);

    /**
     * Get all companies
     * this method gets the list of all the companies in the sql table
     *
     * @return - returns a list of the companies exists in DB
     */
    //Read
    List<Company> getAllCompanies();

    /**
     * Get one company
     * this method gets a company from the sql table by:
     *
     * @param companyId - the company's id as identifier
     * @return Company - returns a company from DB
     */
    //Read
    Company getOneCompany(int companyId);

    /**
     * Get all coupons by company
     * this method will return a coupons list of one company
     *
     * @param companyId - gives the id of the company we want it's coupons list
     * @return - returns a list of coupons of the company
     */
    List<Coupon> getAllCouponsByCompany(int companyId);
}
