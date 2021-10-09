package DB;

import Beans.Config;

/**
 * The Data base manager class
 * managing the SQL queries, and the configuration
 */
public class DatabaseManager {
    //Fields
    /**
     * url field - the sql site address
     */
    public static String url = "jdbc:mysql://localhost:3306?createDatabaseIfNotExist=FALSE&useTimezone=TRUE&serverTimezone=GMT"; //Asia/Jerusalem
    /**
     * username field - the user's name
     */
    public static String username = "root";
    /**
     * password field - the user's password
     */
    public static String password = "";

    /**
     * CREATE_DB field - sql statement that creates the data base in the sql
     */
    private static String CREATE_DB = "CREATE DATABASE  luxuryCoupons";
    /**
     * DROP_DB field - sql statement that delete the data base in the sql
     */
    private static String DROP_DB = "DROP DATABASE luxuryCoupons";
    /**
     * CREATE_TABLE_COMPANIES field - sql statement that creates the company table
     */
    private static final String CREATE_TABLE_COMPANIES = "CREATE TABLE IF NOT EXISTS `luxuryCoupons`.`companies` ( `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY , `name` VARCHAR(150) NOT NULL , `email` VARCHAR(150) NOT NULL , `password` VARCHAR(150) NOT NULL )";
    /**
     * DROP_TABLE_COMPANIES field - sql statement that delete the company table in the sql
     */
    private static final String DROP_TABLE_COMPANIES = "DROP TABLE `luxuryCoupons`.`companies`";
    /**
     * CREATE_TABLE_CUSTOMERS field - sql statement that creates the customers table
     */
    private static final String CREATE_TABLE_CUSTOMERS = "CREATE TABLE IF NOT EXISTS `luxuryCoupons`.`customers` ( `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY , `firstName` VARCHAR(150) NOT NULL , `lastName` VARCHAR(150) NOT NULL , `email` VARCHAR(150) NOT NULL , `password` VARCHAR(150) NOT NULL)";
    /**
     * DROP_TABLE_CUSTOMERS field - sql statement that delete the customers table in the sql
     */
    private static final String DROP_TABLE_CUSTOMERS = "DROP TABLE `luxuryCoupons`.`customers`";
    /**
     * CREATE_TABLE_CATEGORIES field - sql statement that creates the categories table
     */
    private static final String CREATE_TABLE_CATEGORIES = "CREATE TABLE IF NOT EXISTS `luxuryCoupons`.`categories` ( `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY , `name` VARCHAR(150) NOT NULL)";
    /**
     * DROP_TABLE_CATEGORIES field - sql statement that delete the categories table in the sql
     */
    private static final String DROP_TABLE_CATEGORIES = "DROP TABLE `luxuryCoupons`.`categories`";
    /**
     * CREATE_TABLE_COUPONS field - sql statement that creates the coupons table
     */
    private static final String CREATE_TABLE_COUPONS = "CREATE TABLE IF NOT EXISTS `luxuryCoupons`.`coupons` (`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY, `companyId` INT NOT NULL ,`amount` INT NOT NULL, `price` DOUBLE NOT NULL , `categoryId` INT NOT NULL, `title` VARCHAR(150) NOT NULL,`description` VARCHAR(150) NOT NULL, `image` VARCHAR(300) NOT NULL,`startDate` DATE NOT NULL,`endDate` DATE NOT NULL, FOREIGN KEY(companyId) REFERENCES companies(id) ON DELETE CASCADE , FOREIGN KEY(categoryId) REFERENCES categories(id))";
    /**
     * DROP_TABLE_COUPONS field - sql statement that delete the coupons table in the sql
     */
    private static final String DROP_TABLE_COUPONS = "DROP TABLE `luxuryCoupons`.`coupons`";
    /**
     * CREATE_TABLE_CUSTOMERS_VS_COUPONS field - sql statement that creates the costumer vs coupons table
     */
    private static final String CREATE_TABLE_CUSTOMERS_VS_COUPONS = "CREATE TABLE IF NOT EXISTS `luxuryCoupons`.`customers_vs_coupons` (`customerId` INT NOT NULL,`couponId` INT NOT NULL, PRIMARY KEY (customerId, couponId), FOREIGN KEY(customerId) REFERENCES customers(id) ON DELETE CASCADE, FOREIGN KEY(couponId) REFERENCES coupons(id) ON DELETE CASCADE)";
    /**
     * DROP_TABLE_CUSTOMERS_VS_COUPONS  field - sql statement that delete the costumer vs coupons table in the sql
     */
    private static final String DROP_TABLE_CUSTOMERS_VS_COUPONS = "DROP TABLE `luxuryCoupons`.`customers_vs_coupons`";



    /**
     * Get configuration
     * this method will create a configuration based on the values in the fields
     */
    public static void getConfiguration() {
        Config config = Config.readConfig();
        //"jdbc:mysql://localhost:3306?createDatabaseIfNotExist=FALSE&useTimezone=TRUE&serverTimezone=Asia/Jerusalem"
        url = "jdbc:mysql://" + config.getSqlConnectionString() +
                "?createDatabaseIfNotExist" + (config.isCreateIfNotExists() ? "TRUE" : "FALSE") +
                "&useTimezone=" + (config.isUseTimeZone() ? "TRUE" : "FALSE") +
                "&serverTimezone=" + config.getServerTimeZone();
        username = config.getUserName();
        password = config.getUserPassword();
        CREATE_DB = "CREATE DATABASE " + config.getDBname();
        DROP_DB = "DROP DATABASE " + config.getDBname();
    }


    /**
     * Create data base
     * creates the db in sql
     */
    public static void createDataBase() {
        DBUtils.runQuery(CREATE_DB);
    }


    /**
     * Drop data base
     * deletes the db from sql
     */
    public static void dropDataBase() {
        DBUtils.runQuery(DROP_DB);
    }


    /**
     * Create table `companies`
     * creates the table in sql
     */
    public static void createTableCompanies() {
        DBUtils.runQuery(CREATE_TABLE_COMPANIES);
    }


    /**
     * Drop table `companies`
     * deletes the table from sql
     */
    public static void dropTableCompanies() {
        DBUtils.runQuery(DROP_TABLE_COMPANIES);
    }


    /**
     * Create table `customers`
     * creates the table in sql
     */
    public static void createTableCustomers() {
        DBUtils.runQuery(CREATE_TABLE_CUSTOMERS);
    }


    /**
     * Drop table `customers`
     * deletes the table from sql
     */
    public static void dropTableCustomers() {
        DBUtils.runQuery(DROP_TABLE_CUSTOMERS);
    }


    /**
     * Create table `categories`
     * creates the table in sql
     */
    public static void createTableCategories() {
        DBUtils.runQuery(CREATE_TABLE_CATEGORIES);
    }


    /**
     * Drop table`categories`
     * deletes the table from sql
     */
    public static void dropTableCategories() {
        DBUtils.runQuery(DROP_TABLE_CATEGORIES);
    }


    /**
     * Create table `coupons`
     * creates the table in sql
     */
    public static void createTableCoupons() {
        DBUtils.runQuery(CREATE_TABLE_COUPONS);
    }


    /**
     * Drop table `coupons`
     * deletes the table from sql
     */
    public static void dropTableCoupons() {
        DBUtils.runQuery(DROP_TABLE_COUPONS);
    }


    /**
     * Create table `customers vs coupons`
     * creates the table in sql
     */
    public static void createTableCustomersVsCoupons() {
        DBUtils.runQuery(CREATE_TABLE_CUSTOMERS_VS_COUPONS);
    }


    /**
     * Drop table `customers vs coupons`
     * deletes the table from sql
     */
    public static void dropTableCustomersVsCoupons() {
        DBUtils.runQuery(DROP_TABLE_CUSTOMERS_VS_COUPONS);
    }


    /**
     * First init method
     * this method creates the config file
     * and initialized the Data Base and the Tables in sql
     */
    public static void firstInit() {
        //Creates the config file
        Config config = new Config();
        //"jdbc:mysql://localhost:3306?createDatabaseIfNotExist=FALSE&useTimezone=TRUE&serverTimezone=Asia/Jerusalem"
        config.setDBname("luxurycoupons");
        config.setSqlConnectionString("localhost:3306");
        config.setCreateIfNotExists(true);
        config.setUseTimeZone(true);
        config.setServerTimeZone("Asia/Jerusalem");
        config.setUserName("root");
        config.setUserPassword("");
        config.saveConfig();
        //Creates the DB
        DatabaseManager.createDataBase();
        DatabaseManager.createTableCategories();
        DatabaseManager.createTableCompanies();
        DatabaseManager.createTableCoupons();
        DatabaseManager.createTableCustomers();
        DatabaseManager.createTableCustomersVsCoupons();
    }


    /**
     * Closing the program and deleting DB
     * this method will check the DROP methods of DB
     * and close all the connections for closing the program safley
     */
    @SuppressWarnings("unused")
    public static void closeProgram() {
        DatabaseManager.dropTableCustomersVsCoupons();
        DatabaseManager.dropTableCustomers();
        DatabaseManager.dropTableCoupons();
        DatabaseManager.dropTableCompanies();
        DatabaseManager.dropTableCategories();
        DatabaseManager.dropDataBase();
        try {
            //Close all the connections
            ConnectionPool.getInstance().closeAllConnection();
        } catch (InterruptedException e) {
            System.out.println("DatabaseManager -'Close program method'" + ":\n" + e.getMessage()+"\n");
        }
        System.exit(0);
    }
}
