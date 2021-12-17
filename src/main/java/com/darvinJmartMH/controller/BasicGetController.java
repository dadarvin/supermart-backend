package com.darvinJmartMH.controller;

import com.darvinJmartMH.Algorithm;
import com.darvinJmartMH.dbjson.JsonTable;
import com.darvinJmartMH.dbjson.Serializable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FunctionalInterface
public interface BasicGetController<T extends Serializable>  {
    /**
     * Method untuk mengambil suatu objek dari list yang sesuai dengan objek dengan id tersebut
     * @param id id dari suatu objek yang ingin di dapatkan
     * @return objek yang ingin dicari atau diambil berdasarkan id atau null jika tidak ditemukan
     */
    @GetMapping("/id")
    default T getById(int id){
        return Algorithm.<T>find(getJsonTable(), (e) -> e.id == id);
    }

    /**
     * Method untuk melakukan paginasi pada collection berdasarkan index page dan ukuran page yang diberikan
     * @param page index page / jumlah halaman
     * @param pageSize ukuran page / jumlah elemen pada satu page
     * @return list yang berisikan elemen sesuai dengan page dan pagesize
     */
    @GetMapping("/page")
    default List<T> getPage (int page, int pageSize){
        return Algorithm.<T>paginate(getJsonTable(), page, pageSize, e->true);
    }
	/**
     * Method untuk mengambil list yang tepad dari tabel json
     * @return list yang sesuai dengan permintaan
     */
    public abstract JsonTable<T> getJsonTable();
}
