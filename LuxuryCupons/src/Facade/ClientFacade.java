package Facade;

import DAO.CompaniesDAO;
import DAO.CouponsDAO;
import DAO.CustomersDAO;

import Exceptions.LuxuryCouponsException;

/**
 * Client facade is an abstract class that indicates all types of clients
 * who can use the system and the login method for them to inherit
 */
@SuppressWarnings("unused")
public abstract class ClientFacade {
    //Fields
    /**
     * companies DAO field is a referance for inheritor classes
     */
    protected CompaniesDAO companiesDAO;
    /**
     * customers DAO field is a referance for inheritor classes
     */
    protected CustomersDAO customersDAO;
    /**
     * companies DAO field is a referance for inheritor classes
     */
    protected CouponsDAO couponsDAO;

    /**
     * Client login method
     *
     * @param email    - the email of the client for login
     * @param password - the password of the client for login
     * @return boolean - returns if the login was successful
     * @throws LuxuryCouponsException - throws an error if the company doesn't exist
     */
    public boolean login(String email, String password) throws LuxuryCouponsException {
        if (this instanceof AdminFacade) {
            AdminFacade adminFacade = new AdminFacade();
            return adminFacade.login(email, password);
        } else if (this instanceof CompanyFacade) {
            CompanyFacade companyFacade = new CompanyFacade();
            return companyFacade.login(email, password);
        } else if (this instanceof CustomerFacade) {
            CustomerFacade customerFacade = new CustomerFacade();
            return customerFacade.login(email, password);
        }
        return false;
    }
}
