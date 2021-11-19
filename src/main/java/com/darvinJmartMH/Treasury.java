package com.darvinJmartMH;


/**
 * Write a description of class PriceTag here.
 *
 * @author Darvin
 * @version 18/09/20
 */
public class Treasury
{
    
    public final static double COMMISSION_MULTIPLIER = 0.05;
    public final static double BOTTOM_PRICE = 20000.0;
    public final static double BOTTOM_FEE = 1000.0;
//    public double discount;
//    public double price;
    
//    //Constructor dengan parameter price
//    public Treasury(double price){
//        this.price = price;
//        this.discount = 0.0;
//    }
//
//    //Constructor dengan parameter price dan discount
//    public Treasury(double price, double discount){
//        this.price = price;
//        this.discount = discount;
//    }
    
    public static double getAdjustedPrice(double price, double discount){
        double afterDisc = getDiscountedPrice(price, discount) + getAdminFee(price, discount);
        return afterDisc;
    }
    
    private static double getDiscountedPrice(double price, double discount){
        if(discount >= 100.0){
            return 0.0;
        } else{
            return (price - (price * discount/100));
        }
    }
    
    public static double getAdminFee(double price, double discount){
        double afterDisc = getDiscountedPrice(price, discount);
        if( afterDisc <= BOTTOM_PRICE){
            return BOTTOM_FEE;
        } else {
            afterDisc = afterDisc - (afterDisc * COMMISSION_MULTIPLIER);
            return afterDisc;
        }
    }
    
}
