package com.darvinJmartMH;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Write a description of class Payment here.
 *
 * @author Darvin
 * @version Proyek akhir OOP
 */
public class Payment extends Invoice
{
    public ArrayList<Record> history = new ArrayList<>();
    public int productCount;
    public Shipment shipment;
    public int storeId;

    public static class Record
    {
        public Status status;
        public Date date;
        public String message;

        /**
         * Constructor untuk menginstansiasi objek Record
         * @param status status dari Record
         * @param message pesan dari record
         */
        public Record(Status status, String message)
        {
            this.status = status;
            this.message = message;
            this.date = Calendar.getInstance().getTime();
        }
    }

    /**
     * Constructor untuk mengisntansiasi objek Payment
     * @param buyerId id dari pembeli (accountid)
     * @param productId id id dari produk yang akan diproses
     * @param productCount jumlah dari produk yang ingin dibayarkan atau dibeli
     * @param shipment tipe shipment yang digunakan pada produk
     * @param storeId id dari toko yang menjual produk tersebut
     */
    public Payment(int buyerId, int productId, int productCount, Shipment shipment, int storeId)
    {
        super(buyerId, productId);
        this.shipment = shipment;
        this.productCount = productCount;
        this.storeId = storeId;
    }

    /**
     * Method untuk menghitung jumlah biaya yang akan dibayarkan
     * @param product produk yang akan dibeli
     * @return harga total produk yang akan dibeli
     */
    public double getTotalPay(Product product)
    {
        return ((productCount * product.price) + shipment.cost);
    }
}
