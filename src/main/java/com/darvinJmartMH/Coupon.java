package com.darvinJmartMH;


/**
 * Write a description of class Coupon here.
 *
 * @author Darvin
 * @version 20/09/2021
 */


public class Coupon extends Serializable
{
    
    public enum Type
    {
        DISCOUNT, REBATE;
    }

    public final String name;
    public final int code;
    public final double cut;
    public final Type type;
    public final double minimum;
    private boolean used;
    double price = 25000;
    double discount = 16;
    
    public Coupon(int id,String name, int code, Type type, double cut, double minimum){
        used = false;
        this.name = name;
        this.code = code;
        this.type = type;
        this.cut = cut;
        this.minimum = minimum;
    }
    
    public boolean isUsed(){
        return used;
    }
    
    public boolean canApply(double price, double discount){
        if(Treasury.getAdjustedPrice(price, discount) >= minimum && used == false){
            return true;
        } else {
            return false;
        }
    }
    
    public double apply(double price, double discount){
        used = true;
        double harga = Treasury.getAdjustedPrice(this.price, discount);
        
        if(type == type.DISCOUNT){
            harga = Treasury.getAdjustedPrice(price, discount);
            return ( harga - (price* (cut/100) ) );
        } else if (type == type.REBATE) {
            return ( harga - this.price);
        } else {
            return harga;
        }
    }
}
