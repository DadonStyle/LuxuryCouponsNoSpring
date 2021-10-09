package Beans;

import java.io.*;

/**
 * The Config class
 * manages the user configuration settings
 */
public class Config implements Serializable {
    //Fields
    /**
     * serial version UID field - used as a class identification number
     */
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    /**
     * sql connection string field - is a part of the url (the sql site address)
     */
    String sqlConnectionString;
    /**
     * create if not exists field - checks if the data base exists already
     */
    boolean createIfNotExists;
    /**
     * use time zone field - if to use a time zone or not
     */
    boolean useTimeZone;
    /**
     * server time zone field - the server's time zone
     */
    String serverTimeZone;
    /**
     * user name field - the user's name
     */
    String userName;
    /**
     * user password field - the user's password
     */
    String userPassword;
    /**
     * DB name field - the data base name
     */
    String DBname;

    /**
     * Default constructor (Beans)
     */
    public Config() {
    }

    /**
     * Constructor - for making config object
     *
     * @param sqlConnectionString - part of the url-the sql site address
     * @param createIfNotExists   - if the data base exists already
     * @param useTimeZone         - if to use a time zone
     * @param serverTimeZone      - the server's time zone
     * @param userName            - the user's name
     * @param userPassword        - the user's password
     * @param DBname              - the data base name
     */
    @SuppressWarnings("unused")
    public Config(String sqlConnectionString, boolean createIfNotExists, boolean useTimeZone, String serverTimeZone, String userName, String userPassword, String DBname) {
        this.sqlConnectionString = sqlConnectionString;
        this.createIfNotExists = createIfNotExists;
        this.useTimeZone = useTimeZone;
        this.serverTimeZone = serverTimeZone;
        this.userName = userName;
        this.userPassword = userPassword;
        this.DBname = DBname;
    }

    /**
     * Save config
     * this method will save the user's configuration in a file-  serialization
     *
     * @return boolean- if it was saved or not
     */
    @SuppressWarnings("UnusedReturnValue")
    public boolean saveConfig() {
        try {
            //we declare the file itself, where we are going to write data....
            FileOutputStream fileOut = new FileOutputStream("luxuryCoupons.config");
            //we will write an object , since we don't know which class type we will be using
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            //write the file on current instance
            out.writeObject(this);
            //close the output stream
            out.close();
            //close the file
            fileOut.close();
            //tell all is ok
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Read config
     * this method will read our configuration from a file- deserialization
     *
     * @return Config- returns the configuration
     */
    public static Config readConfig() {
        Config returnResult = new Config();
        //choose the file that we will read from
        try {
            //point to the file that we will be reading from
            FileInputStream fileIn = new FileInputStream("luxuryCoupons.config");
            //create an object from the file
            ObjectInputStream in = new ObjectInputStream(fileIn);
            //get the data as config file (by the fields that we used)
            returnResult = (Config) in.readObject();
            //close inputStream
            in.close();
            //close the file
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return returnResult;
    }

    //Getters & Setters

    /**
     * Get sql connection string
     *
     * @return String- part of the url-the sql site address
     */
    public String getSqlConnectionString() {
        return sqlConnectionString;
    }

    /**
     * Set sql connection String
     *
     * @param sqlConnectionString- gets the sql site address as part of the url
     */
    public void setSqlConnectionString(String sqlConnectionString) {
        this.sqlConnectionString = sqlConnectionString;
    }

    /**
     * Is create if not exists
     *
     * @return boolean- returns if the data base exists already
     */
    public boolean isCreateIfNotExists() {
        return createIfNotExists;
    }

    /**
     * Set create if not exists
     *
     * @param createIfNotExists-sets if the the data base exists by the param it gets (true/false)
     */
    public void setCreateIfNotExists(boolean createIfNotExists) {
        this.createIfNotExists = createIfNotExists;
    }

    /**
     * Is use time zone
     *
     * @return boolean-returns if to use a time zone
     */
    public boolean isUseTimeZone() {
        return useTimeZone;
    }

    /**
     * Set use time zone
     *
     * @param useTimeZone-sets the is use time zone by the param it gets (true/false)
     */
    public void setUseTimeZone(boolean useTimeZone) {
        this.useTimeZone = useTimeZone;
    }

    /**
     * Get server time zone
     *
     * @return String- returns the server's time zone
     */
    public String getServerTimeZone() {
        return serverTimeZone;
    }

    /**
     * Set the server time zone
     *
     * @param serverTimeZone- sets the server's time zone by the param it gets
     */
    public void setServerTimeZone(String serverTimeZone) {
        this.serverTimeZone = serverTimeZone;
    }

    /**
     * Get user name
     *
     * @return String- returns the user's name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set user name
     *
     * @param userName-sets the user name by the param it gets
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get user password
     *
     * @return String- returns the user's password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Set user password
     *
     * @param userPassword-sets the user's password by the param it gets
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Get DB name
     *
     * @return String- returns the data base name
     */
    public String getDBname() {
        return DBname;
    }

    /**
     * Set DB name
     *
     * @param DBname- sets the data base name by the param it gets
     */
    public void setDBname(String DBname) {
        this.DBname = DBname;
    }

    /**
     * To string method
     *
     * @return String- the config details for display
     */
    @Override
    public String toString() {
        return "Config{" +
                "sqlConnectionString='" + sqlConnectionString + '\'' +
                ", createIfNotExists=" + createIfNotExists +
                ", useTimeZone=" + useTimeZone +
                ", serverTimeZone='" + serverTimeZone + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", DBname='" + DBname + '\'' +
                "}\n";
    }
}

