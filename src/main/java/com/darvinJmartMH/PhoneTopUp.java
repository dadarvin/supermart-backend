package com.darvinJmartMH;

public class PhoneTopUp extends Invoice{
    public String phoneNumber;
    public Status status;

    /**
     * Constructor untuk mengisntansiasi objek PhoneTopUp
     * @param buyerId id pembeli (accountid)
     * @param productId id dari produk
     * @param phoneNumber nomor telepon pembeli
     */
    public PhoneTopUp(int buyerId, int productId, String phoneNumber) {
        super(buyerId, productId);
        this.phoneNumber = phoneNumber;
    }

    /**
     * Method untuk menghitung total biaya yang harus dibayarkan dari interface invoice
     * @param product produk yang akan dibeli
     * @return harga total produk yang akan dibeli
     */
    @Override
     public double getTotalPay(Product product){
        return product.price;
    }
}
