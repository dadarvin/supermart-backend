package com.darvinJmartMH;
import com.darvinJmartMH.dbjson.Serializable;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Class untuk melakukan komplen produk
 *
 * @author Darvin
 * @version Modul 3
 */
public class Complaint extends Serializable
{
    public Date date;
    public String desc;
    
    /*
     * @param id berasal dari parent class
     * @param desc menjelaskan keterangan complaint
     */
    public Complaint(int id, String desc){
        this.desc = desc;
        this.date = new Date();
    }

    /**
     * Method untuk menampilkan informasi complaint dalam bentuk string
     * @return mengembalikan informasi complaint
     */
    public String toString()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String str = "Complaint{date=" + sdf.format(this.date) + ", desc='" + this.desc + "'}";
        return str;
    }
}
