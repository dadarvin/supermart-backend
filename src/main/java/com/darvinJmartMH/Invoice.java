package com.darvinJmartMH;
import com.darvinJmartMH.dbjson.Serializable;

import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;

/**
 * Class untuk invoice akun maupun toko
 *
 * @author Darvin
 * @version Proyek Akhir OOP
 */
public abstract class Invoice extends Serializable
{
    public Date date;
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating = Rating.NONE;
    public Status status;

    //Enum untuk status dan rating pengiriman
    public enum Status{
        WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT,
        FINISHED, FAILED, DELIVERED;
    }
    enum Rating{
        NONE, BAD, NEUTRAL, GOOD;
    }
    /**
     * Constructor untuk menginstansiasi objek Invoice
     * @param buyerId id dari pembeli / id dari account pembeli
     * @param productId id dari produk
     */
    protected Invoice(int buyerId, int productId){
        this.date = Calendar.getInstance().getTime();
        this.buyerId = buyerId;
        this.productId = productId;
        this.rating = Rating.NONE;
        this.complaintId = -1;
    }

    /**
     * Method untuk menghitung total biaya yang perlu dibayarkan
     * @param product produk yang akan dibeli
     * @return harga total produk yang akan dibeli
     */
    public abstract double getTotalPay(Product product);

}
