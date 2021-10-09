package Login;

import Exceptions.LuxuryCouponsException;
import Facade.AdminFacade;
import Facade.ClientFacade;
import Facade.CompanyFacade;
import Facade.CustomerFacade;

/**
 * The Login manager is a singletone
 * contains a Login method, that allows all the client types to connect to the system
 */
public class LoginManager {
    //Field
    /**
     * my login manager field - the class's instance
     */
    private static LoginManager myLoginManager;

    /**
     * Default constructor
     */
    private LoginManager() {
    }

    /**
     * Get instance constructor
     * a static Singleton constructor
     *
     * @return new Login manager instance if don't exist, or if it already exist return it
     */
    public static LoginManager getInstance() {
        if (myLoginManager == null) {
            synchronized (LoginManager.class){
                if(myLoginManager == null){
                    myLoginManager = new LoginManager();
                }
            }
        }
        return myLoginManager;
    }

    /**
     * Login method
     * will login a specific client type: Admin, Company, or Customer
     *
     * @param email      - the email of the client for login
     * @param password   - the password of the client for login
     * @param clientType - a specific client type
     * @return client facade instance
     * @throws LuxuryCouponsException - throws exceptions if the client doesn't exist
     */
    @SuppressWarnings("UnusedReturnValue")
    public ClientFacade Login(String email, String password, ClientType clientType) throws LuxuryCouponsException {
        ClientFacade adminFacade = new AdminFacade();
        ClientFacade companyFacade = new CompanyFacade();
        ClientFacade customerFacade = new CustomerFacade();
        switch (clientType) {
            case ADMINISTRATOR -> {
                if (adminFacade.login(email, password)) return adminFacade;
            }
            case COMPANY -> {
                if (companyFacade.login(email, password)) return companyFacade;
            }
            case CUSTOMER -> {
                if (customerFacade.login(email, password)) return customerFacade;
            }
        }
        return null;
    }
}
