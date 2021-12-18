package com.darvinJmartMH;


/**
 * class untuk pengaturan harga produk
 *
 * @author Darvin
 * @version 18/09/20
 */
public class Treasury
{
    
    public final static double COMMISSION_MULTIPLIER = 0.05;
    public final static double BOTTOM_PRICE = 20000.0;
    public final static double BOTTOM_FEE = 1000.0;
    
	/**
     * Method untuk mendapatkan harga yang disesuaikan dengan diskon dan biaya admin
     * @param price harga awal produk
     * @param discount persentase diskon pada produk
     * @return harga setelah disesuaikan dengan potongan
     */
    public static double getAdjustedPrice(double price, double discount){
        double afterDisc = getDiscountedPrice(price, discount) + getAdminFee(price, discount);
        return afterDisc;
    }
    /**
     * Method untuk mendapatkan harga yang sudah dikurangi diskon
     * @param price harga awal produk
     * @param discount persentase diskon
     * @return harga setelah dilakukan potongan
     */
    private static double getDiscountedPrice(double price, double discount){
        if(discount >= 100.0){
            return 0.0;
        } else{
            return (price - (price * discount/100));
        }
    }
    /**
     * Method untuk mendapatkan biaya admin berdasarkan harga yang telah dipotong
     * @param price harga awal produk
     * @param discount persentase diskon pada produk
     * @return biaya admin yang diterapkan
     */
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
