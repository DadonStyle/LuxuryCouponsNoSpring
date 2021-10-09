package DAO;

/**
 * The Categories DAO interface
 * indicates which methods we will implement in the categories DBDAO class
 */
public interface CategoriesDAO {

    /**
     * Add all categories
     * adds enum categories to `categories` table
     */
    void addAllCategories();
}
