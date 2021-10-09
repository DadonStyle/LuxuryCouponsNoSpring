package Beans;

import java.util.Calendar;
import java.util.Date;

/**
 * The Coupon class
 * represent the coupon information managed by the application
 */
public class Coupon {
    //Fields
    /**
     * id, companyId, amount - fields
     */
    private int id, companyId, amount;
    /**
     * price field
     */
    private double price;
    /**
     * Category field
     */
    private Category category;
    /**
     * title, description, image - fields
     */
    private String title, description, image;
    /**
     * startDate and endDate - fields
     */
    private Date startDate, endDate;

    /**
     * Default Coupon constructor(Beans)
     */
    public Coupon() {
    }

    /**
     * Program Coupon constructor for creating a coupon object
     *
     * @param companyId   - The ID of the company who owns the coupon
     * @param amount      - The amount of coupons available when initialized
     * @param price       - The coupon's price
     * @param category    - The coupon's category
     * @param title       - The title of the coupon
     * @param description - The description of the coupon's details
     * @param image       - The coupon's image for display
     * @param startDate   - The date when the coupon is published and available for use
     * @param endDate     - The date when the coupon will be expired
     */
    public Coupon(int companyId, int amount, double price, Category category, String title, String description, String image, Date startDate, Date endDate) {
        this(0, companyId, amount, price, category, title, description, image, startDate, endDate);
    }

    /**
     * Coupon constructor for creating a coupon object from sql (DB)
     *
     * @param id          - The sql auto increment id for the coupon in the DB table
     * @param companyId   - The ID of the company who owns the coupon
     * @param amount      - The amount of coupons available when initialized
     * @param price       - The coupon's price
     * @param category    - The coupon's category
     * @param title       - The title of the coupon
     * @param description - The description of the coupon's details
     * @param image       - The coupon's image for display
     * @param startDate   - The date when the coupon is published and available for use
     * @param endDate     - The date when the coupon will be expired
     */
    public Coupon(int id, int companyId, int amount, double price, Category category, String title, String description, String image, Date startDate, Date endDate) {
        this.id = id;
        this.companyId = companyId;
        this.amount = amount;
        this.price = price;
        this.category = category;
        this.title = title;
        this.description = description;
        this.image = image;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //Getters & Setters

    /**
     * Getter method
     *
     * @return Int - returns the sql coupon's id in the DB table
     */
    public int getId() {
        return id;
    }


    /**
     * Getter method
     *
     * @return Int - returns the company id of the company that owns the coupon
     */
    public int getCompanyId() {
        return companyId;
    }


    /**
     * Getter method
     *
     * @return Int - returns the amount of a coupon that available for purchase
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Setter method
     *
     * @param amount - sets the amount of a coupon that is available by the param it gets
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Getter method
     *
     * @return Double - returns the price of a coupon
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter method
     *
     * @param price - Sets the price for a coupon by the param it gets
     */
    @SuppressWarnings("unused")
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter method
     *
     * @return Category - returns the category of a coupon
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Get category ID
     *
     * @return Int - the id of the category for using it on sql
     */
    public int getCategoryId() {
        return this.category.id;
    }

    /**
     * Setter method
     *
     * @param category - Sets the category of a coupon by the param it gets
     */
    @SuppressWarnings("unused")
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Getter method
     *
     * @return String - returns the coupon's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter method
     *
     * @param title - Sets the coupon's title by the param it gets
     */
    @SuppressWarnings("unused")
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter method
     *
     * @return String - returns the coupon's description/details
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter method
     *
     * @param description - sets the coupon's description by the param it gets
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter method
     *
     * @return String - returns the image string of the coupon
     */
    public String getImage() {
        return image;
    }

    /**
     * Setter method
     *
     * @param image - sets the coupon's image string by the param it gets
     */
    @SuppressWarnings("unused")
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Getter method
     *
     * @return Date - returns the date when the coupon was published and available for the first time
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Setter method
     *
     * @param startDate - sets the time when the coupon was published by the param it gets
     */
    @SuppressWarnings("unused")
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Getter method
     *
     * @return Date - returns the date when the coupon will be expired
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Setter method
     *
     * @param endDate - sets the date when the coupon will be expire by the param it gets
     */
    @SuppressWarnings("unused")
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    /**
     * Get custom date
     * To set date properly with Calender for `start date` or `End date` and then to cast it to Date for sql
     *
     * @param day   - The day of the month we want to insert
     * @param month - The month we want to insert
     * @param year  - The year we want to insert
     * @return - java.sql.Date - return the date for the sql
     */
    public static java.sql.Date getCustomDate(int year, int month, int day) {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR, year);
        date.set(Calendar.MONTH, month);
        date.set(Calendar.DAY_OF_MONTH, day);
        date.add(Calendar.MONTH, (-1));
        return new java.sql.Date(date.getTimeInMillis());
    }

    /**
     * To string method
     *
     * @return String - returns the coupon's info for display
     */
    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                "| companyId=" + companyId +
                "| amount=" + amount +
                "| price=" + price +
                "| category=" + category +
                "| title='" + title + '\'' +
                "| description='" + description + '\'' +
                "| image='" + image + '\'' +
                "| startDate=" + startDate +
                "| endDate=" + endDate +
                "}\n";
    }
}
