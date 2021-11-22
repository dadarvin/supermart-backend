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
 * Write a description of class Jmart here.
 *
 * @author Darvin
 * @version 18/09/2021
 */

@SpringBootApplication
public class Jmart
{
    public static long DELIVERED_LIMIT_MS = 1;
    public static long ON_DELIVERY_LIMIT_MS = 2;
    public static long ON_PROGRESS_LIMIT_MS = 3;
    public static long WAITING_CONF_LIMIT_MS = 4;

    class Product{
        public int accountId;
        public ProductCategory category;
        public boolean conditionUsed;
        public double discount;
        public String name;
        public double price;
        public int shipmentPlans;
        public int weight;
        public int id;
    }

    public static boolean paymentTimeKeeper(Payment payment){
        if(payment.history.size() == 0){
            return true;
        }else {
            Payment.Record record = payment.history.get(payment.history.size() - 1);
            long elapsed = System.currentTimeMillis() - record.date.getTime();
            if (record.status.equals(Invoice.Status.WAITING_CONFIRMATION) && (elapsed > WAITING_CONF_LIMIT_MS)) {
                record.status = Invoice.Status.FAILED;
                return true;
            } else if (record.status.equals(Invoice.Status.ON_PROGRESS) && (elapsed > ON_PROGRESS_LIMIT_MS)) {
                record.status = Invoice.Status.FAILED;
                return true;
            } else if (record.status.equals(Invoice.Status.ON_DELIVERY) && (elapsed > ON_PROGRESS_LIMIT_MS)) {
                record.status = Invoice.Status.DELIVERED;
                return true;
            } else if (record.status.equals(Invoice.Status.DELIVERED) && (elapsed > DELIVERED_LIMIT_MS)) {
                record.status = Invoice.Status.FINISHED;
                return true;
            } else {
                return false;
            }
        }
    }

    public static List<com.darvinJmartMH.Product> filterByAccountId (List<Product> list, int accountId, int page, int pageSize){
        //Menggunakan interface Predicate untuk memberikan kondisi
        Predicate<Product> predicate = p -> (p.accountId == accountId);

        return paginate(list, page, pageSize, predicate);
    }

    public static List<Product> filterByCategory (List<Product> list, ProductCategory category){
        List<Product> filteredList = new ArrayList<Product>();

        for(Product p : list){
            if( p.category == category){
                filteredList.add(p);
            }
        }

        return filteredList;
    }

    public static List<Product> filterByName (List<Product> list, String search, int page, int pageSize){
        //Menggunakan interface Predicate untuk mencari kata pada variabel search
        Predicate<Product> predicate = p -> (p.name.toLowerCase().contains(search.toLowerCase()));

        return paginate(list, page, pageSize, predicate);
    }

    public static List<Product> filterByPrice (List<Product> list, double minPrice, double maxPrice){
        List<Product> filteredList = new ArrayList<Product>();
        if(minPrice != 0.0 && maxPrice != 0.0) {
            for(Product p : list) {
                double prodPrice = p.price;
                if(prodPrice > minPrice && prodPrice < maxPrice) {
                    filteredList.add(p);
                }
            }
        } else if(minPrice == 0.0) {
            for(Product p : list) {
                double prodPrice = p.price;
                if(prodPrice < maxPrice) {
                    filteredList.add(p);
                }
            }
        } else if(maxPrice == 0.0) {
            for(Product p : list) {
                double prodPrice = p.price;
                if(prodPrice > minPrice) {
                    filteredList.add(p);
                }
            }
        }
        return filteredList;
    }

    public static void main(String args[]){

        JsonDBEngine.Run(Jmart.class);
        SpringApplication.run(Jmart.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() ->JsonDBEngine.join()));

//        String password = "password";
//        String hashedPass = null;
//        try {
//
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            md.update(password.getBytes());
//
//            byte[] bytes = md.digest();
//
//            StringBuilder sb = new StringBuilder();
//            for(int i = 0; i < bytes.length; i++){
//                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
//            }
//            hashedPass = sb.toString();
//            System.out.println(hashedPass);
//        } catch( NoSuchAlgorithmException e){
//            e.printStackTrace();
//        }

//        try{
//            // sesuaikan argument dibawah dengan lokasi resource file yang Anda unduh di EMAS!
//            JsonTable<Payment> table = new JsonTable<>(Payment.class, "C:\\@Pen&Pin\\Dar\\OOP\\OOP_prak\\jmart\\src\\goldenSample\\randomPaymentList.json");
//            // membuat thread untuk payment pool
//            ObjectPoolThread<Payment>paymentPool =new ObjectPoolThread<Payment>("Thread-pp", Jmart::paymentTimeKeeper);
//            // menjalankan thread (ingat menggunakan start bukan run), run melakukan instruksi dalam current thread
//            paymentPool.start();
//            //tambahkan seluruh payment hasil baca ke dalam pool
//            table.forEach(payment ->paymentPool.add(payment));
//            // berikan sinyal untuk keluar dari routine apabila seluruh objek telah di proses
//            while (paymentPool.size() != 0);
//            paymentPool.exit();
//            // tunggu hingga thread selesai di eksekusi
//            while (paymentPool.isAlive());
//            // thread telah berhasil di selesaikan
//            System.out.println("Thread exited successfully");
//            // cek hasil output
//            Gson gson = new Gson();
//            table.forEach(payment -> {
//                String history = gson.toJson(payment.history);
//                System.out.println(history);
//            });

//            String filepath = "C:\\@Pen&Pin\\Dar\\OOP\\OOP_prak\\jmart\\src\\goldenSample\\tes.json";
//
//            JsonTable<Account> tableAccount = new JsonTable<Account>(Account.class, filepath);
//            tableAccount.add(new Account("name", "email", "password", 120.0));
//            tableAccount.writeJson();
//
//            tableAccount = new JsonTable<>(Account.class, filepath);
//            tableAccount.forEach(
//                    account -> System.out.println(account.toString())
//            );
//        } catch (Throwable t){
//            t.printStackTrace();
//        }
    }

    private static List<Product> paginate (List<Product> list, int page, int pageSize, Predicate<Product> pred){
        List<Product> filteredList = new ArrayList<Product>();

        int tempIndex = (page * pageSize);

        for(Product p : list){
            if(pred.predicate(p)){
                filteredList.add(p);
            }
        }

//        List<Product> paginatedList = new ArrayList<Product>();
//        // Membagi Arraylist dalam bentuk sublist sesuai page dan pageSize
//        for(int i = tempIndex; i < tempIndex + pageSize; i++){
//            paginatedList.add(filteredList.get(i));
//        }
        List<Product> paginatedList = filteredList.subList(tempIndex, (tempIndex+pageSize));

        return paginatedList;
    }

    public static List<Product> read (String filepath) throws FileNotFoundException
    {
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(new FileReader(filepath));
        Product[] products = gson.fromJson(jsonReader, Product[].class);

        List<Product> list = new ArrayList<>();
        Collections.addAll(list, products);

        return list;
    }

}
