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

    public JsonTable<Coupon> getJsonTable(){
        return couponTable;
    }

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