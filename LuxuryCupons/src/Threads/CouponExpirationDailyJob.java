package Threads;

import Beans.Coupon;
import DBDAO.CouponsDBDAO;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * The Coupon expiration daily job class
 * is a thread that runs in the background and deletes expired coupons every day
 */
public class CouponExpirationDailyJob implements Runnable {
    //Fields
    /**
     * quit field indicates the `daily job` thread status
     */
    private boolean quit;

    /**
     * Constructor
     *
     * @param quit - sets true - for stoping, or false - for starting the tread's job
     */
    public CouponExpirationDailyJob(boolean quit) {
        this.quit = quit;
    }

    /**
     * Run method
     * this method runs the thread once a day
     * if the coupon date expired it will delete from sql coupons table
     * and all the purchase on this coupon will deleted too
     */
    @Override
    public void run() {
        while (!quit) {
            try {
                //Works once a day
                TimeUnit.HOURS.sleep(24);
                CouponsDBDAO dbdao = new CouponsDBDAO();
                List<Coupon> couponList = dbdao.getAllCoupons();
                for (Coupon item : couponList) {
                    java.util.Date currentDate = new Date(new java.util.Date().getTime());
                    //checks if the coupon is expired
                    if (currentDate.after(item.getEndDate())) {
                        //deletes the purchase history of the coupon in the sql table
                        dbdao.deleteCouponPurchaseByCouponId(item.getId());
                        //deletes the coupon from the sql table
                        dbdao.deleteCoupon(item.getId());
                        System.out.println("Coupon was expired and deleted from DB : " + item.getTitle()+"\n");
                    }
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                System.out.println("Coupon expiration daily job -'Run' method" +":\n" + e.getMessage()+"\n");
            }
        }
    }

    /**
     * Stop method
     * this method will stop the thread
     */
    @SuppressWarnings("unused")
    public void stop() {
        quit = true;
    }
}
