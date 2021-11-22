package com.darvinJmartMH.controller;

import com.darvinJmartMH.*;
import com.darvinJmartMH.dbjson.JsonAutowired;
import com.darvinJmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;
import com.darvinJmartMH.Product;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController implements BasicGetController<Product> {
    @JsonAutowired(value = Product.class, filepath = "productFile.json")

    public static JsonTable<Product> productTable;

    public JsonTable<Product> getJsonTable(){
        return productTable;
    }

    @GetMapping("/create")
    Product create(
            @RequestParam int accountId,
            @RequestParam String name,
            @RequestParam int weight,
            @RequestParam boolean conditionUsed,
            @RequestParam double price,
            @RequestParam double discount,
            @RequestParam ProductCategory category,
            @RequestParam byte shipmentPlans
    ){
        Product product = Algorithm.<Product>find(productTable, obj -> obj.accountId == accountId);
        Account account = Algorithm.<Account>find(AccountController.accountTable, obj -> obj.id == accountId);

        if(product == null || account.store != null){
            return null;
        } else {
            Product newProduct = new Product(accountId, name, weight, conditionUsed, price, discount, category, shipmentPlans);
            productTable.add(newProduct);
            return newProduct;
        }
    }

    @GetMapping("/{id}/store")
    List<Product> getProductByStore(
            @PathVariable int id,
            @RequestParam int page,
            @RequestParam int pageSize
    ){
        return Algorithm.<Product>paginate(getJsonTable(), page, pageSize, product -> product.accountId == id);
    }

    @GetMapping("/getFiltered")
    List<Product> getProductFiltered(
            @RequestParam int page,
            @RequestParam int pageSize,
            @RequestParam(defaultValue = "1") int accountId,
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int minPrice,
            @RequestParam(defaultValue = "1000") int maxPrice,
            @RequestParam(required = false) ProductCategory category
    ){
        List<Product> newList = new ArrayList<>();
//        if(accountId != 0){
//            newList = Jmart.filterByAccountId(newList, accountId, page, pageSize);
//        }
//        if(!search.equals("")){
//            newList = Jmart.filterByName(newList, search, page, pageSize);
//        }
//        if(minPrice != 0.0 || maxPrice != 0.0){
//            newList = Jmart.filterByPrice(newList, minPrice, maxPrice);
//        }
//        if(category != null){
//            newList = Jmart.filterByCategory(newList, category);
//        }
        return newList;
    }


}
