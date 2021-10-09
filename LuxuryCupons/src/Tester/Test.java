package Tester;

import Beans.*;
import DB.DatabaseManager;
import DBDAO.CategoriesDBDAO;
import DBDAO.CouponsDBDAO;
import Facade.AdminFacade;
import Facade.CompanyFacade;
import Facade.CustomerFacade;
import Login.ClientType;
import Login.LoginManager;
import Threads.CouponExpirationDailyJob;


/**
 * The Test class
 * contains one method that test the entire system by calling each of the functions of the business logic
 */
public class Test {

    /**
     * Test all
     * this method will check all the system's methods
     */
    public static void testAll() {

        try {
            //Starts the daily job
            CouponExpirationDailyJob couponExpirationDailyJob = new CouponExpirationDailyJob(false);
            new Thread(couponExpirationDailyJob).start();

            //create first init for the database
            DatabaseManager.firstInit();

            //Create the categories
            CategoriesDBDAO categoriesDBDAO = new CategoriesDBDAO();
            categoriesDBDAO.addAllCategories();

            //Admin facade methods
            AdminFacade adminFacade = new AdminFacade();
            LoginManager.getInstance().Login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
            Company company1 = new Company("Louis Vuitton", "LV@lv.com", "IluvLouis");
            Company company2 = new Company("Channel", "channel@chanel.com", "channelCosmetics");
            Company company3 = new Company("Shabur", "Shabur@Shabur.com", "asafGranit");
            adminFacade.addCompany(company1);
            adminFacade.addCompany(company2);
            adminFacade.addCompany(company3);
            company1 = adminFacade.getOneCompany(1);
            company1.setPassword("for_change");
            adminFacade.updateCompany(company1);
            System.out.println("All the companies: \n" + adminFacade.getAllCompanies() + "\n");
            System.out.println("One company: " + adminFacade.getOneCompany(2) + "\n");
            Customer customer1 = new Customer("Daniel", "Peretz", "daniel@gmail.com", "daniel1234");
            Customer customer2 = new Customer("Noam", "Dadon", "noam@gmail.com", "noam999");
            Customer customer3 = new Customer("Shiran", "Yacobi", "shiran@gmail.com", "shiran555");
            adminFacade.addCustomer(customer1);
            adminFacade.addCustomer(customer2);
            adminFacade.addCustomer(customer3);
            customer1 = adminFacade.getOneCustomer(1);
            customer1.setPassword("for_change");
            adminFacade.updateCustomer(customer1);
            System.out.println("All the customers: \n" + adminFacade.getAllCustomers() + "\n");
            System.out.println("One customer: " + adminFacade.getOneCustomer(1) + "\n");
            adminFacade.deleteCustomer(2);
            adminFacade.deleteCompany(2); //Deletes channel

            //Company facade methods
            CompanyFacade companyFacade = new CompanyFacade(1);
            LoginManager.getInstance().Login("LV@lv.com", "for_change", ClientType.COMPANY);
            Coupon coupon1 = new Coupon(companyFacade.getCompanyId(), 50, 10_000.0, Category.FASHION, "Bag", "Bran new season 2021 bag", "bagbagbagbag", Coupon.getCustomDate(2021, 4, 10), Coupon.getCustomDate(2021, 10, 10));
            Coupon coupon2 = new Coupon(companyFacade.getCompanyId(), 20, 500.00, Category.COSMETICS, "Body cream", "musk body cream", "LVLVLVLVLV", Coupon.getCustomDate(2021, 5, 1), Coupon.getCustomDate(2021, 5, 31));
            Coupon coupon3 = new Coupon(companyFacade.getCompanyId(), 10, 1_000, Category.FASHION, "LV boots", "LV new boots on sale", "bootsboots", Coupon.getCustomDate(2021, 3, 20), Coupon.getCustomDate(2021, 4, 30));
            companyFacade.addCoupon(coupon1);
            companyFacade.addCoupon(coupon2);
            companyFacade.addCoupon(coupon3);
            coupon1 = new CouponsDBDAO().getOneCoupon(1);
            coupon1.setDescription("for_change");
            companyFacade.updateCoupon(coupon1);
            company1.setCoupons(companyFacade.getCompanyCoupons());
            System.out.println("All LV coupons:\n" + company1.getCoupons() + "\n");
            System.out.println("All the LV coupons in cosmetic category: \n" + companyFacade.getCompanyCoupons(Category.COSMETICS) + "\n");
            System.out.println("All the LV coupons under 1,000$ : \n" + companyFacade.getCompanyCoupons(1_000) + "\n");
            System.out.println("LV details: " + companyFacade.getCompanyDetails() + "\n");
            companyFacade.deleteCoupon(2); //Deletes the Body cream coupon

            //Customer facade methods
            CustomerFacade customerFacade = new CustomerFacade(1);
            LoginManager.getInstance().Login("daniel@gmail.com", "for_change", ClientType.CUSTOMER);
            customerFacade.purchaseCoupon(coupon1);
            customerFacade.purchaseCoupon(new CouponsDBDAO().getOneCoupon(3));
            System.out.println(customerFacade.getCustomerID() + "- Daniel's coupons: \n" + customerFacade.getCustomerCoupons() + "\n");
            System.out.println("Daniel's coupons in Fashion category: " + customerFacade.getCustomerCoupons(Category.FASHION) + "\n");
            System.out.println("Daniel's coupons under 2,000$: " + customerFacade.getCustomerCoupons(2_000) + "\n");
            System.out.println("Daniel's info: " + customerFacade.getCustomerDetails() + "\n");

            System.out.println(Config.readConfig());


            //Stops the daily job
            //couponExpirationDailyJob.stop();

            //Closing the program
            //DatabaseManager.closeProgram();

        } catch (Exception err) {
            System.out.println("Test -'Test all' method" + ":\n" + err.getMessage());
        }
    }
}