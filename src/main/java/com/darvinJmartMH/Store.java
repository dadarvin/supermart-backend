package com.darvinJmartMH;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class untuk toko dari akun
 *
 * @author Darvin
 * @version Proyek Akhir OOP
 */
public class Store 
{
    public static final String REGEX_NAME = "^[A-Z](?!.*(\\s)\1).{4,20}$";
    public static final String REGEX_PHONE = "^\\d{9,12}$"; 
    public String name;
    public String address;
    public String phoneNumber;
    public double balance;

    /**
     * Constructor untuk menginstansiasi objek Store
     * @param name nama toko
     * @param address alamat toko
     * @param phoneNumber nomor telepon toko
     * @param balance balance yang dimiliki toko
     */
    public Store(String name, String address, String phoneNumber, double balance){
        this.name = name;
        this.address= address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    /**
     * Method untuk menampilkan informasi toko
     * @return string yang berisikan informasi-informasi dari toko
     */
    public String toString()
    {
        return "name: " + this.name + 
        "\naddress: " + this.address + 
        "\nphoneNumber: " + this.phoneNumber;
    }
    
    /**
     * Method untuk melakukan validasi terhadap nama toko dan nomor telepon
     * @return true = validated, false = not validated
     */
    
    public boolean validate(){
        Pattern namePattern = Pattern.compile(REGEX_NAME);
        Pattern phonePattern = Pattern.compile(REGEX_PHONE);
        Matcher nameMatcher = namePattern.matcher(this.name);
        Matcher phoneMatcher = phonePattern.matcher(this.phoneNumber);
        boolean nameMatch = nameMatcher.find();
        boolean phoneMatch = phoneMatcher.find();
        
        return (nameMatch && phoneMatch);
    }
}
