package com.darvinJmartMH;
import com.darvinJmartMH.dbjson.Serializable;

/**
 * Write a description of class Product here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Product extends Serializable {
    public int accountId;
    public ProductCategory category;
    public boolean conditionUsed;
    public double discount;
    public String name;
    public double price;
    public byte shipmentPlans;
    public int weight;
    
    /**
     * Constructor untuk menginstansiasi objek Product
     * @param accountId berupa id akun atau store yang memiliki produk
     * @param name nama produk
     * @param weight berat produk
     * @param conditionUsed kondisi dari produk (baru / second)
     * @param price harga produk
     * @param discount diskon produk
     * @param category kategori produk
     * @param shipmentPlans jenis shipment delivery yang digunakan untuk mengirim produk
     */
    public Product(int accountId, String name, int weight, boolean conditionUsed, double price, double discount, ProductCategory category, byte shipmentPlans){
        this.accountId = accountId;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.price = price;
        this.discount = discount;
        this.category = category;
        this.shipmentPlans = shipmentPlans;
    }

    /**
     * Method untuk menampilkan informasi-informasi dari objek Product
     * @return string berisi informasi-informasi dari objek Product
     */
    public String toString(){
        return "Name: " + this.name +
        "\nWeight: " + this.weight +
        "\nconditionUsed: " + this.conditionUsed +
        "\nPrice: " + this.price +
        "\ncategory: " + this.category +
        "\ndiscount: " + this.discount +
        "\naccountId: " + this.accountId;
    }
}
