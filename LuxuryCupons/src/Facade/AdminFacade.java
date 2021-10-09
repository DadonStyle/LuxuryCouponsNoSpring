package Facade;

import Beans.Company;
import Beans.Customer;
import DAO.CompaniesDAO;
import DAO.CustomersDAO;
import DBDAO.CompaniesDBDAO;
import DBDAO.CustomersDBDAO;
import Exceptions.LuxuryCouponsException;

import java.util.ArrayList;
import java.util.List;

/**
 * The Admin facade class
 * extends the clinet facade for the login method
 * and indicates the methods that the administrator can perform
 */
@SuppressWarnings("FieldCanBeLocal")
public class AdminFacade extends ClientFacade {
    //Fields
    /**
     * Email field- the email of the admin
     */
    private final String EMAIL = "admin@admin.com";
    /**
     * Passowrd field- the password of the admin
     */
    private final String PASSWORD = "admin";
    /**
     * companies DAO field for using all it's methods
     */
    private final CompaniesDAO companiesDAO;
    /**
     * customers DAO field for using all it's methods
     */
    private final CustomersDAO customersDAO;
    /**
     * companies field is a list of all the companies that will be created in the program
     */
    private List<Company> companies;
    /**
     * customers field is a list of all the customers that will be created in the program
     */
    private List<Customer> customers;
    /**
     * report name field is a final string that represnts the class name for the custom exception
     */
    private static final String REPORT_NAME = "Admin Facade";

    /**
     * Default constructor
     */
    public AdminFacade() {
        companiesDAO = new CompaniesDBDAO();
        customersDAO = new CustomersDBDAO();
        companies = new ArrayList<>();
        customers = new ArrayList<>();
    }

    /**
     * Override method - login
     * will login the client type of administrator
     *
     * @param email    - the email of the client for login
     * @param password - the password of the client for login
     * @return - boolean, returns if the login was successful
     * @throws LuxuryCouponsException - throws an error if the email or password are inncorrect
     */
    @Override
    public boolean login(String email, String password) throws LuxuryCouponsException {
        if (email.equals(EMAIL) && password.equals(PASSWORD)) {
            System.out.println("Logged in successfully - Admin!"+"\n");
            return true;
        }
        throw new LuxuryCouponsException("Something went wrong ! \n Please check your email and password", REPORT_NAME);
    }

    /**
     * Add company
     * adds object of company to sql table
     *
     * @param company - the company object we want to add to the sql table
     * @throws LuxuryCouponsException - throws an error if the comapny alreay exists
     */
    public void addCompany(Company company) throws LuxuryCouponsException {
        companies = companiesDAO.getAllCompanies();
        if (companies.size() > 0) {
            for (Company item : companies) {
                //check if there's a company with the same name or email
                if (company.getEmail().equals(item.getEmail()) || item.getName().equals(company.getName())) {
                    throw new LuxuryCouponsException("Company " + company.getName() + " already exists", REPORT_NAME);
                }
            }
        }
        System.out.println("Company " + company.getName() + " added to the system"+"\n");
        companiesDAO.addCompany(company);
    }

    /**
     * Update company
     * Updates the values of existing company in the sql table
     *
     * @param company - gets the company object with the new values to update
     */
    public void updateCompany(Company company) {
        companiesDAO.updateCompany(company);
    }

    /**
     * Delete company
     * deletes a company from sql table
     *
     * @param companyId - get the company's id as identifier
     * @throws LuxuryCouponsException - throws an error if the company id doean't exist
     */
    public void deleteCompany(int companyId) throws LuxuryCouponsException {
        boolean isDone;
        isDone=companiesDAO.deleteCompany(companyId);
        System.out.println("Company " + companyId + " deleted successfully!"+"\n");
        if (!isDone) throw new LuxuryCouponsException("Company id doesn't exist", REPORT_NAME);
    }

    /**
     * Get all companies
     * gets all the companies from sql
     *
     * @return list of all the companies in DB
     */
    public List<Company> getAllCompanies() {
        return new ArrayList<>(companiesDAO.getAllCompanies());
    }

    /**
     * Get one company
     * gets a company from sql
     *
     * @param companyId - gets a company by id as identifier
     * @return Company - return a single company from DB
     * @throws LuxuryCouponsException - throws an error if the company id doesn't exist
     */
    public Company getOneCompany(int companyId) throws LuxuryCouponsException {
        Company company = companiesDAO.getOneCompany(companyId);
        if (company == null) {
            throw new LuxuryCouponsException("The company doesn't exist", REPORT_NAME);
        }
        return company;
    }

    /**
     * Add customer
     * adds object of customer to sql table
     *
     * @param customer - the customer object we want to add to the sql table
     * @throws LuxuryCouponsException -throws an error if the customer alredy exists
     */
    public void addCustomer(Customer customer) throws LuxuryCouponsException {
        customers = customersDAO.getAllCustomers();
        if (customers.size() > 0) {
            if (customersDAO.isCustomerExist(customer.getEmail(), customer.getPassword())) {
                throw new LuxuryCouponsException("Customer already exists", REPORT_NAME);
            }
        }
        System.out.println("Customer " + customer.getFirstName() + " " + customer.getLastName() + " added to the system"+"\n");
        customersDAO.addCustomer(customer);
    }

    /**
     * Update customer
     * Updates the values of existing customer in the sql table
     *
     * @param customer - gets the customer object with the new values to update
     */
    public void updateCustomer(Customer customer) {
        customersDAO.updateCustomer(customer);
    }

    /**
     * Delete customer
     * deletes a customer from sql table
     *
     * @param customerId - get the customer's id as identifier
     * @throws LuxuryCouponsException - throw an error if the customer id dosn't exist
     */
    public void deleteCustomer(int customerId) throws LuxuryCouponsException {
        boolean isDone;
        isDone = customersDAO.deleteCustomer(customerId);
        System.out.println("Customer " + customerId + " deleted successfully!"+"\n");
        if (!isDone) throw new LuxuryCouponsException("Customer id doesn't exist", REPORT_NAME);
    }

    /**
     * Get all customers
     * gets all the customers from sql
     *
     * @return list of all the customers in DB
     */
    public List<Customer> getAllCustomers() {
        return customersDAO.getAllCustomers();
    }

    /**
     * Get one customer
     * gets a customer from sql
     *
     * @param customerId - gets a customer by id as identifier
     * @return Customer - return a single customer from DB
     * @throws LuxuryCouponsException - throws an error if the customer id doesn't exist
     */
    public Customer getOneCustomer(int customerId) throws LuxuryCouponsException {
        Customer customer = customersDAO.getOneCustomer(customerId);
        if (customer == null) {
            throw new LuxuryCouponsException("The customer doesn't exist", REPORT_NAME);
        }
        return customer;
    }
}
