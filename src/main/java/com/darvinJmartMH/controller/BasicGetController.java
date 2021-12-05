package com.darvinJmartMH.controller;

import com.darvinJmartMH.Algorithm;
import com.darvinJmartMH.dbjson.JsonTable;
import com.darvinJmartMH.dbjson.Serializable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FunctionalInterface
public interface BasicGetController<T extends Serializable>  {
    @GetMapping("/id")
    default T getById(int id){
        return Algorithm.<T>find(getJsonTable(), (e) -> e.id == id);
    }

    @GetMapping("/page")
    default List<T> getPage (int page, int pageSize){
        return Algorithm.<T>paginate(getJsonTable(), page, pageSize, e->true);
    }

    public abstract JsonTable<T> getJsonTable();
}
