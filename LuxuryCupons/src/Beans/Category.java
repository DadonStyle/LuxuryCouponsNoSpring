package Beans;

/**
 * The Category enum
 * specifies which categories of coupons the companies can have
 */
public enum Category {

    /**
     * category type - Food
     */
    FOOD(1),
    /**
     * category type - Electricity
     */
    ELECTRICITY(2),
    /**
     * category type - Restaurant
     */
    RESTAURANT(3),
    /**
     * category type - Vacation
     */
    VACATION(4),
    /**
     * category type - Fashion
     */
    FASHION(5),
    /**
     * category type - Cosmetics
     */
    COSMETICS(6);

    //Fields
    /**
     * id field - for each of the enums types
     */
    int id;

    /**
     * Constructor for creating a category object with id from sql table
     *
     * @param id - sync with the sql category id
     */
    Category(int id) {
        this.id = id;
    }

    /**
     * Get categories
     * Inserts all the categories into an array for adding it to the sql table
     *
     * @return an array of all the categories in strings
     */
    public static String[] getCategories() {
        int numOfCategories = 6;
        String[] categories = new String[numOfCategories];
        categories[0] = "FOOD";
        categories[1] = "ELECTRICITY";
        categories[2] = "RESTAURANT";
        categories[3] = "VACATION";
        categories[4] = "FASHION";
        categories[5] = "COSMETICS";
        return categories;
    }

    /**
     * Get category by ID
     * Get category by id for getting the data from sql to the program
     *
     * @param id - the category id synced with the sql table `categories`
     * @return Category for getting data from sql to the program
     */
    public static Category getCategoryById(int id) {
        return switch (id) {
            case 1 -> FOOD;
            case 2 -> ELECTRICITY;
            case 3 -> RESTAURANT;
            case 4 -> VACATION;
            case 5 -> FASHION;
            case 6 -> COSMETICS;
            default -> null;
        };
    }
}
