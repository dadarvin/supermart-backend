package DarvinJmartMH;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

/**
 * Write a description of class Jmart here.
 *
 * @author Darvin
 * @version 18/09/2021
 */

public class Jmart
{
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

    public static List<Product> filterByAccountId (List<Product> list, int accountId, int page, int pageSize){
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
//        System.out.println("Account Id: " + new Account(null, null, null, -1).id);
//        System.out.println("Account Id: " + new Account(null, null, null, -1).id);
//        System.out.println("Account Id: " + new Account(null, null, null, -1).id);

//        System.out.println("Payment Id: " + new Payment(-1, -1, -1, null).id);
//        System.out.println("Payment Id: " + new Payment(-1, -1, -1, null).id);
//        System.out.println("Payment Id: " + new Payment(-1, -1, -1, null).id);

        try{
//            List<Product> list = read("C:\\@Pen&Pin\\Dar\\OOP\\OOP_prak\\jmart\\src\\goldenSample\\randomProductList.json");
//
//            List<Product> filtered = filterByPrice(list, 0.0, 20000.0);
//            filtered.forEach(product -> System.out.println(product.price));
//
//            List<Product> filteredName = filterByName(list, "AMD", 0, 5);
//            filteredName.forEach(product -> System.out.println(product.name));
//
//            List<Product> filteredAccount = filterByAccountId(list, 3,1, 3);
//            filteredAccount.forEach(product -> System.out.println(product.name));

            String filepath = "C:\\@Pen&Pin\\Dar\\OOP\\OOP_prak\\jmart\\src\\goldenSample\\tes.json";

            JsonTable<Account> tableAccount = new JsonTable<Account>(Account.class, filepath);
            tableAccount.add(new Account("name", "email", "password", 120.0));
            tableAccount.writeJson();

            tableAccount = new JsonTable<>(Account.class, filepath);
            tableAccount.forEach(
                    account -> System.out.println(account.toString())
            );
        } catch (Throwable t){
            t.printStackTrace();
        }
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
