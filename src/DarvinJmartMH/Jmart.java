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
//    class Country{
//        public String name;
//        public int population;
//        public List<String> listOfStates;
//    }

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

    public static List<Product> filterByCategory (List<Product> list, ProductCategory category){
        List<Product> filteredList = new ArrayList<Product>();

        for(Product p : list){
            if( p.category == category){
                filteredList.add(p);
            }
        }

        return filteredList;
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
        System.out.println("Account Id: " + new Account(null, null, null, -1).id);
        System.out.println("Account Id: " + new Account(null, null, null, -1).id);
        System.out.println("Account Id: " + new Account(null, null, null, -1).id);

        System.out.println("Payment Id: " + new Payment(-1, -1, -1, null).id);
        System.out.println("Payment Id: " + new Payment(-1, -1, -1, null).id);
        System.out.println("Payment Id: " + new Payment(-1, -1, -1, null).id);

        try{
            List<Product> list = read("C:\\@Pen&Pin\\Dar\\OOP\\OOP_prak\\jmart\\src\\goldenSample\\randomProductList.json");
//            List<Product> list = read("../goldenSample/randomProductList.json");
            List<Product> filtered = filterByPrice(list, 0.0, 20000.0);
            filtered.forEach(product -> System.out.println(product.price));
        } catch (Throwable t){
            t.printStackTrace();
        }
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

}
