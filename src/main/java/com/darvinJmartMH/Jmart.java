package com.darvinJmartMH;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import com.darvinJmartMH.dbjson.JsonDBEngine;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Class jmart sebagai main untuk menjalankan framework spring
 *
 * @author Darvin
 * @version 18/09/2021
 */

@SpringBootApplication
public class Jmart
{
    public static long DELIVERED_LIMIT_MS = 10;
    public static long ON_DELIVERY_LIMIT_MS = 10;
    public static long ON_PROGRESS_LIMIT_MS = 10;
    public static long WAITING_CONF_LIMIT_MS = 10;

    class Country
    {
        public String name;
        public int population;
        public List<String> listOfStates;
    }

    public static void main(String[] args)
    {
        JsonDBEngine.Run(Jmart.class);
        SpringApplication.run(Jmart.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));


    }

    public static boolean paymentTimekeeper(Payment payment){
        Date timeNow = Calendar.getInstance().getTime();
        if(payment.history.size() != 0){
            Payment.Record lastRecord = payment.history.get(payment.history.size() - 1);
            long timePassed = timeNow.getTime() - lastRecord.date.getTime();
            if(lastRecord.status == Invoice.Status.WAITING_CONFIRMATION && (timePassed > WAITING_CONF_LIMIT_MS)){
                payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Gagal"));
                return true;
            }
            else if((lastRecord.status == Invoice.Status.ON_PROGRESS) && (timePassed > ON_PROGRESS_LIMIT_MS)){
                payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Gagal"));
                return true;
            }
            else if(lastRecord.status == Invoice.Status.ON_DELIVERY && timePassed > ON_DELIVERY_LIMIT_MS){
                payment.history.add(new Payment.Record(Invoice.Status.DELIVERED, "Terkirim"));
                return true;
            }
            else if(lastRecord.status == Invoice.Status.DELIVERED && timePassed > DELIVERED_LIMIT_MS){
                payment.history.add(new Payment.Record(Invoice.Status.FINISHED, "Selesai"));
                return true;
            }
        }
        return false;
    }

    public static List<Product> read(String filepath) throws FileNotFoundException
    {
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(new FileReader(filepath));
        Product[] products = gson.fromJson(jsonReader, Product[].class);
        List<Product> list = new ArrayList<>();
        Collections.addAll(list, products);
        return list;
    }

    public static List<Product> filterByPrice(List<Product> list, double minPrice, double maxPrice)
    {
        List<Product> newList = new ArrayList<Product>();
        if(minPrice != 0.0 && maxPrice != 0.0)
        {
            for(Product p : list)
            {
                double productPrice = p.price;
                if(productPrice > minPrice && productPrice < maxPrice)
                {
                    newList.add(p);
                }
            }
        }
        else if(minPrice == 0.0)
        {
            for(Product p : list)
            {
                double productPrice = p.price;
                if(productPrice < maxPrice)
                {
                    newList.add(p);
                }
            }
        }
        else if(maxPrice == 0.0)
        {
            for(Product p : list)
            {
                double productPrice = p.price;
                if(productPrice > minPrice)
                {
                    newList.add(p);
                }
            }
        }
        return newList;
    }

    public static List<Product> filterByCategory(List<Product> list, ProductCategory category)
    {
        List<Product> newList = new ArrayList<Product>();
        for(Product p : list)
        {
            if(p.category == category)
            {
                newList.add(p);
            }
        }
        return newList;
    }

    private static List<Product> paginate(List<Product> list, int page, int pageSize, Predicate<Product> pred){
        List<Product> newList = new ArrayList<>();
        for(Product p : list)
        {
            if(pred.predicate(p))
            {
                newList.add(p);
            }
        }
        int startIndex = page * pageSize;
        int endIndex = startIndex + pageSize;
        List<Product> paginatedList;
        if(endIndex > newList.size())
        {
            paginatedList = newList.subList(startIndex, newList.size());
        }
        else
        {
            paginatedList = newList.subList(startIndex, endIndex);
        }
        return paginatedList;
    }

    public static List<Product> filterByName(List<Product> list, String search, int page, int pageSize){
        String searchLowercase = search.toLowerCase();
        Predicate<Product> predicate = product -> product.name.toLowerCase().contains(searchLowercase);
        List<Product> newList = paginate(list, page, pageSize, predicate);
        return newList;
    }

    public static List<Product> filterByAccountId(List<Product> list, int accountId, int page, int pageSize){
        Predicate<Product> predicate = product -> (product.accountId == accountId);
        List<Product> newList = paginate(list, page, pageSize, predicate);
        return newList;
    }
}
