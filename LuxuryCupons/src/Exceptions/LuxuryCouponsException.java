package Exceptions;

/**
 * The Luxury coupons exception class
 * uses custom exception in case of incorrect information sent to the other classes in the program
 */
public class LuxuryCouponsException extends Exception {
    //Field
    /**
     * Field report name- represents in which class the error accured
     */
    @SuppressWarnings("unused")
    private String reportName;
    /**
     * Field start message states the starting string of all the custom exceptions in the program
     */
    private final static StringBuffer START_MESSAGE = new StringBuffer("Luxury coupons Exception: ");

    /**
     * Default constructor
     */
    @SuppressWarnings("unused")
    public LuxuryCouponsException() {
    }

    /**
     * Constructor for creating new exception
     *
     * @param text       - the custom message for the report
     * @param reportName - the class which we will throw the exception from
     */
    public LuxuryCouponsException(String text, String reportName) {
        super(START_MESSAGE + text + " " + reportName+"\n");
    }
}
