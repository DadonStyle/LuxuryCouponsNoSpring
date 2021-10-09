package Facade;

import Beans.Category;
import Beans.Company;
import Beans.Coupon;
import DAO.CompaniesDAO;
import DAO.CouponsDAO;
import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDBDAO;
import Exceptions.LuxuryCouponsException;

import java.util.LinkedList;
import java.util.List;

/**
 * The Company facade class
 * extends the clinet facade and indicates the methods that the company can perform
 */
public class CompanyFacade extends ClientFacade {
    //Fields
    /**
     * company id field- the compny's id
     */
    private int companyId;
    /**
     * companies DAO field for using all it's methods
     */
    private final CompaniesDAO companiesDAO;
    /**
     * coupons DAO field for using all it's methods
     */
    private final CouponsDAO couponsDAO;
    /**
     * report name field is a final string that represnts the class name for the custom exception
     */
    private static final String REPORT_NAME = "Company Facade";

    /**
     * Default constructor
     */
    public CompanyFacade() {
        companiesDAO = new CompaniesDBDAO();
        couponsDAO = new CouponsDBDAO();
    }

    /**
     * Constructor that takes company id
     *
     * @param companyId - for specified the company by id
     */
    public CompanyFacade(int companyId) {
        this.companyId = companyId;
        companiesDAO = new CompaniesDBDAO();
        couponsDAO = new CouponsDBDAO();
    }

    //Getter

    /**
     * Get company id
     *
     * @return int- returns the company's id
     */
    public int getCompanyId() {
        return companyId;
    }

    /**
     * Override method - login
     * will login the client type of Company
     *
     * @param email    - the email of the client for login
     * @param password - the password of the client for login
     * @return - boolean, returns if the login was successful
     * @throws LuxuryCouponsException - throws an error if the company doesn't exist
     */
    @Override
    public boolean login(String email, String password) throws LuxuryCouponsException {
        if (companiesDAO.isCompanyExist(email, password)) {
            System.out.println("Logged in successfully - Company!"+"\n");
            return true;
        }
        throw new LuxuryCouponsException("Something went wrong ! \n Please check your email and password", REPORT_NAME);
    }

    /**
     * Add coupon
     * adds object of coupon to sql table
     *
     * @param coupon - the coupon object we want to add to the sql table
     * @throws LuxuryCouponsException - throws an error if the coupon's name already exists in this company
     */
    public void addCoupon(Coupon coupon) throws LuxuryCouponsException {
        List<Coupon> coupons = companiesDAO.getAllCouponsByCompany(this.companyId);
        if (coupons.size() > 0) {
            for (Coupon item : coupons) {
                if (item.getTitle().equals(coupon.getTitle())) {
                    throw new LuxuryCouponsException("A coupon with the same title cannot be added to the same company in,", REPORT_NAME);
                }
            }
        }
        couponsDAO.addCoupon(coupon);
        System.out.println(coupon.getTitle() + " added to the company " + companyId+"\n");
    }

    /**
     * Update coupon
     * Updates the values of existing coupon in the sql table
     *
     * @param coupon - gets the coupon object with the new values to update
     */
    public void updateCoupon(Coupon coupon) {
        couponsDAO.updateCoupon(coupon);
    }

    /**
     * Delete coupon
     * deletes a coupon from sql table
     *
     * @param couponId - get the coupon's id as identifier
     * @throws LuxuryCouponsException - throws an error if the coupon id doesn't exist
     */
    public void deleteCoupon(int couponId) throws LuxuryCouponsException {
        boolean isDone;
        isDone = couponsDAO.deleteCoupon(couponId);
        System.out.println("Coupon " + couponId + " deleted successfully!"+"\n");
        if (!isDone) throw new LuxuryCouponsException("Coupon id doesn't exist", REPORT_NAME);
    }

    /**
     * Get all coupons
     * get all coupons of a specific company that`s was login to the system
     *
     * @return list of all the coupons in DB of a specific company
     */
    public List<Coupon> getCompanyCoupons() {
        return companiesDAO.getAllCouponsByCompany(this.companyId);
    }

    /**
     * Get coupons by category
     * get all coupons of a specific company that`s was login to the system, by specific category
     *
     * @param category - get the category id as identifier
     * @return list of all the coupons in DB of a specific company, by specific category
     */
    public List<Coupon> getCompanyCoupons(Category category) {
        LinkedList<Coupon> coupons = new LinkedList<>(getCompanyCoupons());
        coupons.removeIf(coupon -> !(coupon.getCategory().equals(category)));
        return coupons;
    }

    /**
     * Get coupons by max price
     * get all coupons of a specific company that`s was login to the system, by max price
     *
     * @param maxPrice - get the max price as identifier
     * @return list of all the coupons in DB of a specific company, by max price
     */
    public List<Coupon> getCompanyCoupons(double maxPrice) {
        LinkedList<Coupon> coupons = new LinkedList<>(getCompanyCoupons());
        coupons.removeIf(coupon -> coupon.getPrice() > maxPrice);
        return coupons;
    }

    /**
     * Get company details
     * get company details of a specific company that`s was login to the system
     *
     * @return company - return the company from DB
     */
    public Company getCompanyDetails() {
        return companiesDAO.getOneCompany(companyId);
    }
}
