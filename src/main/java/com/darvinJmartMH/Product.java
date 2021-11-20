package com.darvinJmartMH;


import com.darvinJmartMH.dbjson.Serializable;

/**
 * Write a description of class Product here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
class Product extends Serializable {
    public int accountId;
    public ProductCategory category;
    public boolean conditionUsed;
    public double discount;
    public String name;
    public double price;
    public byte shipmentPlans;
    public int weight;

    
    /**
     * Menambahkan overloading constructor
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

    /*
     * @return Mengembalikan informasi pemilik produk
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
