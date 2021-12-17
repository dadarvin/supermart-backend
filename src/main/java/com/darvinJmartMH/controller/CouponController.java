package com.darvinJmartMH.controller;

import com.darvinJmartMH.Algorithm;
import com.darvinJmartMH.Coupon;
import com.darvinJmartMH.dbjson.JsonAutowired;
import com.darvinJmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/coupon")
public class CouponController implements BasicGetController<Coupon> {
    @JsonAutowired(value = Coupon.class, filepath = "couponFile.json")
    public static JsonTable<Coupon> couponTable;

    /**
     * Method untuk memberikan list yang berisikan objek Coupon yang terdaftar pada file json
     * @return list yang berisikan objek Coupon yang terdaftar pada file json
     */
    public JsonTable<Coupon> getJsonTable(){
        return couponTable;
    }

    /**
     * Method untuk mendapatkan informasi dari suatu objek Coupon berdasrakan id nya
     * @param id id dari coupun yang ingin diperoleh informaisnya
     * @return true jika sudah digunakan atau false jika belum digunakan
     */
    @GetMapping("/{id}/isUsed")
    boolean isUsed(
            @PathVariable int id
    ){
        for(Coupon c : couponTable){
            if(c.id == id){
                return c.isUsed();
            }
        }
        return false;
    }

    /**
     * Method untuk mengecek apakah objek Coupon dengan id tersebut dapat diterapkan
     * @param id id dari objek Coupon yang akan diterapkan
     * @param price harga produk
     * @param discount diskon produk
     * @return true jika Coupon dapat diterapkan atau false jika Coupon tidak dapat diterapkan
     */
    @GetMapping("/{id}/canApply")
    boolean canApply(
            @PathVariable int id,
            @RequestParam double price,
            @RequestParam double discount
    ){
        for(Coupon c : couponTable){
            if(c.id == id){
                return c.canApply(price, discount);
            }
        }
        return false;
    }

    /**
     * Method untuk mencari seluruh objek Coupun yang dapat diterapkan atau available
     * lalu melakukan paginasi pada coupon yang tersedia berdasarkan index dan ukuran page
     * @param page index page
     * @param pageSize ukuran page
     * @return list yang berisikan objek coupon berdasarkan page dan pagesize
     */
    @GetMapping("/getAvailable")
    List<Coupon> getAvailable(
            @RequestParam int page,
            @RequestParam int pageSize
    ){
        ArrayList<Coupon> newList = new ArrayList<>();
        for(Coupon c : couponTable){
            if(!c.isUsed()){
                newList.add(c);
            }
        }
        return Algorithm.<Coupon>paginate(newList, page, pageSize, c -> true);
    }
}