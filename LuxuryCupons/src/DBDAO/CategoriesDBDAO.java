package DBDAO;

import Beans.Category;
import DAO.CategoriesDAO;
import DB.ConnectionPool;
import DB.DBUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * The Categories DBDAO class
 * exacute categories DAO
 */
public class CategoriesDBDAO implements CategoriesDAO {

    //Fields - prepared statements
    /**
     * Add all categories field- is an sql prepared statement
     */
    private static final String ADD_ALL_CATEGORIES = "INSERT INTO `luxuryCoupons`.`categories` (`name`) VALUES (?)";

    /**
     * Constructor
     * gets ConnectionPool instance
     */
    public CategoriesDBDAO() {
        ConnectionPool.getInstance();
    }

    /**
     * Add all categories
     * adds all the categories to the `categories` table
     */
    @Override
    public void addAllCategories() {
        try {
            //Inserts the values
            String[] categories = Category.getCategories();
            for (String category : categories) {
                Map<Integer, Object> params = new HashMap<>();
                //our params....
                params.put(1, category);
                DBUtils.runQuery(ADD_ALL_CATEGORIES, params);
            }
        } catch (Exception err) {
            System.out.println("CategoriesDBDAO -'Add all categories' method" + ":\n" + err.getMessage()+"\n");
        }
    }
}
