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
    @JsonAutowired(value = Product.class, filepath = "fileProduct.json")
    public static JsonTable<Product> productTable;

    /**
     * Method untuk mengembalikan list berisi objek Product dari tabel json
     * @return list objek produk
     */
    public JsonTable<Product> getJsonTable(){
        return productTable;
    }

    /**
     * Method untuk mendapatkan objek Product berdasarkan id
     * @param id id dari objek Product yang ingin didapatkan
     * @return objek produk dengan id dari parameter
     */
    @GetMapping("/{id}")
    Product getProductById(@PathVariable int id) { return getById(id); }

    /**
     * Method untuk mendapatkan list objek Product berdasarkan tokonya dengan paginasi
     * @param id id store yang ingin didapatkan objek Product-nya
     * @param page index page
     * @param pageSize ukuran page
     * @return list yang berisikan objek Product pada store tersebut dengan paginasi
     */
    @GetMapping("/{id}/store")
    List<Product> getProductByStore(@PathVariable int id, @RequestParam int page, @RequestParam int pageSize){
        return Algorithm.<Product>paginate(getJsonTable(), page, pageSize, product -> product.accountId == id);
    }

    /**
     * Method untuk membuat objek Product dan mendaftarkannya pada tabel json
     * @param accountId id store yang membuat objek Product
     * @param name nama objek Product yang akan dibuat
     * @param weight berat objek Product yang akan dibuat
     * @param conditionUsed kondisi objek produk (Baru atau second)
     * @param price harga objek Product yang akan dibuat
     * @param discount diskon objek Product yang akan dibuat
     * @param category kategori objek Product yang akan dibuat
     * @param shipmentPlans tipe shipment yang akan digunakan untuk pengiriman produk
     * @return objek Product yang berhasil dibuat atau null jika tidak berhasil membuat objek Product
     */
    @PostMapping("/create")
    Product create(@RequestParam int accountId, @RequestParam String name, @RequestParam int weight, @RequestParam boolean conditionUsed,
                   @RequestParam double price, @RequestParam double discount, @RequestParam ProductCategory category, @RequestParam byte shipmentPlans){
        for(Account account : AccountController.accountTable){
            if(account.id == accountId && account.store != null){
                Product p = new Product(accountId, name, weight, conditionUsed, price, discount, category, shipmentPlans);
                productTable.add(p);
                return p;
            }
        }
        return null;
    }

    /**
     * Method untuk mendapatkan list yang objek produk yang telah di filter
     * @param page index page
     * @param pageSize ukuran dari page
     * @param accountId id store
     * @param search string yang dicari oleh user
     * @param minPrice harga minimum produk
     * @param maxPrice harga maksimum produk
     * @param category kategori produk
     * @return list yang berisikan objek Product yang memenuhi kondisi filter dan sudah dipaginasi
     */
    @GetMapping("/getFiltered")
    List<Product> getProductFiltered(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "0") int accountId, @RequestParam(defaultValue = "") String search,
                                     @RequestParam(defaultValue = "0") int minPrice, @RequestParam(defaultValue = "0") int maxPrice, @RequestParam(defaultValue = "") ProductCategory category){
//        List<Product> newList = getJsonTable();
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
//        return newList;
        String searchLowercase = search.toLowerCase();
        Predicate<Product> predicateSearch = product -> product.name.toLowerCase().contains(searchLowercase);
        return paginateProductListFilteredAll(page, pageSize, predicateSearch, minPrice, maxPrice, category);
    }

    /**
     * Method untuk mendapatkan list yang berisikan objek Product berdasarkan kondisi filter yang diberikan
     * @param page index page
     * @param pageSize ukuran page
     * @param predSearch functional interface yang berfungsi untuk melakukan pengecekan string search dari user
     * @param minPrice harga minimum produk
     * @param maxPrice harga maksimum produk
     * @param category kategori produk
     * @return list yang berisikan objek Product yang memenuhi kondisi filter dan sudah dipaginasikan
     */
    public List<Product> paginateProductListFilteredAll(int page, int pageSize, Predicate<Product> predSearch, int minPrice, int maxPrice, ProductCategory category){
        List<Product> newList = new ArrayList<Product>();
        if(minPrice != 0.0 && maxPrice != 0.0)
        {
            for(Product p : getJsonTable())
            {
                double productPrice = p.price;
                if(productPrice > minPrice && productPrice < maxPrice && predSearch.predicate(p) && p.category == category)
                {
                    newList.add(p);
                }
            }
        }
        else if(minPrice == 0.0 && maxPrice != 0.0)
        {
            for(Product p : getJsonTable())
            {
                double productPrice = p.price;
                if(productPrice < maxPrice && predSearch.predicate(p) && p.category == category)
                {
                    newList.add(p);
                }
            }
        }
        else if(maxPrice == 0.0 && minPrice != 0.0)
        {
            for(Product p : getJsonTable())
            {
                double productPrice = p.price;
                if(productPrice > minPrice && predSearch.predicate(p) && p.category == category)
                {
                    newList.add(p);
                }
            }
        }
        else {
            for(Product p : getJsonTable())
            {
                double productPrice = p.price;
                if(predSearch.predicate(p) && p.category == category)
                {
                    newList.add(p);
                }
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
}