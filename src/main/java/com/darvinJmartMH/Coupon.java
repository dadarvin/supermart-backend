package com.darvinJmartMH;


import com.darvinJmartMH.dbjson.Serializable;

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
    /**
     * Constructor untuk menginstansiasi objek coupun
     * @param name name of coupon
     * @param code code of coupon
     * @param type Type of coupon (discount or rebate)
     * @param cut percentage of product discount
     * @param minimum minimum price of product need to be paid after discount
     */
    public Coupon(int id,String name, int code, Type type, double cut, double minimum){
        used = false;
        this.name = name;
        this.code = code;
        this.type = type;
        this.cut = cut;
        this.minimum = minimum;
    }
    /**
     * Method untuk mengembalikan status valid coupon (used or not)
     * @return mengembalikan nilai used
     */
    public boolean isUsed(){
        return used;
    }
    /**
     * Method untuk menentukan objek coupun dapat digunakan pada pembelian tersebut
     * dengan syarat harga setelah dipotong lebih dari minimum harga AND belum digunakan
     * @param price harga
     * @param discount persentase diskon yang akan diterapkan
     * @return true=coupon memenuhi syarat ; false=tidak memenuhi
     */
    public boolean canApply(double price, double discount){
        if(Treasury.getAdjustedPrice(price, discount) >= minimum && used == false){
            return true;
        } else {
            return false;
        }
    }
    /**
     * Method untuk menerapkan coupon
     * @param price harga produk yang ingin dipotong berdasarkan coupon
     * @param discount persentase diskon yang akan diterapkan
	 * @param used menandakan coupon digunakan
     * @return harga potongan produk setelah coupon diterapkan
     */
    public double apply(double price, double discount){
        used = true;
        double harga = Treasury.getAdjustedPrice(this.price, discount);
        
        if(type == Type.DISCOUNT){
            
			return (100 - cut)/100 * harga;
        } else if (type == Type.REBATE) {
            return ( harga - this.price);
        } 
	
		return 0.0;
    }
}
