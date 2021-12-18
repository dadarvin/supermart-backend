package com.darvinJmartMH;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Class yang berisi beberapa algoritma untuk functionality aplikasi
 */
public class Algorithm {
    private Algorithm(){
    }

    /**
     * Method untuk menambahkan elemen pada list jika elemen dalam array tersebut sesuai dengan value
     * @param <T> => menspesifikkan jenis class dari suatu objek
     * @param array => array yang dipassing untuk kemudian difilter
     * @param value => nilai sebagai parameter pembanding pada predicate
     * @return mengembalikan list dengan elemen yang sesuai dari hasil filter predicate
     */
    public static <T> List<T> collect(T[] array, T value)
    {
        List<T> list = new ArrayList<T>();
        Predicate<T> p = value1 -> (value1.equals(value));
        for(T element: array)
        {
            if(p.predicate(element))
            {
                list.add(element);
            }
        }
        return list;
    }

    /**
     * Method untuk mengambil elemen yang sesuai dengan paramter value dari sebuah Iterable (dapat berupa list, array, atau hashmap)
     * hasil dari elemen tersebut kemudian akan di return atau kembalikan pada pemanggil
     * @param <T> => jenis class dari suatu objek
     * @param iterable => objek iterable yang berisikan elemen-elemen yang ingin difilter
     * @param value => nilai value yang akan dibandingkan untuk memfilter elemen-elemen array
     * @return list yang berisikan elemen yang memiliki value dengan parameter value
     */
    public static <T> List<T> collect(Iterable<T> iterable, T value)
    {
        List<T> list = new ArrayList<T>();
        Predicate<T> p = value1 -> (value1.equals(value));
        for(T element: iterable)
        {
            if(p.predicate(element))
            {
                list.add(element);
            }
        }
        return list;
    }

    /**
     * Method untuk mendapatkan elemen sesuai dengan value yang diberikan pada paramter dari sebuah objek iterator
     * untuk menemukannya dalam collection
     * @param <T> => jenis class dari suatu objek
     * @param iterator => suatu objek iterator yang akan me-return elemen-elemen dari suatu collection yang ingin difilter
     * @param value => nilai value yang akan dibandingkan untuk memfilter elemen-elemen array
     * @return list yang berisikan elemen yang memiliki value dengan parameter value
     */
    public static <T> List<T> collect(Iterator<T> iterator, T value)
    {
        List<T> list = new ArrayList<T>();
        Predicate<T> p = value1 -> (value1.equals(value));
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(p.predicate(element))
            {
                list.add(element);
            }
        }
        return list;
    }

    /**
     * Method untuk mendapatkan sebuah elemen yang memenuhi ketentuan predicate dari array yang diberikan
     * @param <T> => jenis class dari suatu objek
     * @param array => array yang berisikan elemen-elemen yang ingin difilter
     * @param pred => sebuah functional interface untuk mengecek suatu kondisi yang akan digunakan untuk melakukan filter
     * @return list yang berisikan elemen yang memenuhi kondisi dari parameter interface predicate
     */
    public static <T> List<T> collect (T[] array, Predicate<T> pred){
        List<T> list = new ArrayList<>();
        for (T element : array) {
            if (pred.predicate(element)) {
                list.add(element);
            }
        }
        return list;
    }

    /**
     * Method untuk mendapatkan elemen yang memeunuhi kondisi dari suatu predicate yang terdapat
     * pada objeck iterable (dapat berupa list, array, atau hashmap)
     * @param <T> => jenis class dari suatu objek
     * @param iterable => objek iterable yang berisikan elemen-elemen yang ingin difilter
     * @param pred => sebuah functional interface untuk mengecek suatu kondisi yang akan digunakan untuk melakukan filter
     * @return list yang berisikan elemen yang memenuhi kondisi dari parameter interface predicate
     */
    public static <T> List<T> collect(Iterable<T> iterable, Predicate<T> pred)
    {
        List<T> list = new ArrayList<T>();
        for(T element: iterable)
        {
            if(pred.predicate(element))
            {
                list.add(element);
            }
        }
        return list;
    }

    /**
     * Method untuk mendapatkan elemen yang memenuhi ketentuan parameter predicate dari suaut
     * objek iterator terutama dalam collection
     * @param <T> => jenis class dari suatu objek
     * @param iterator => suatu objek iterator yang akan me-return elemen-elemen dari suatu collection yang ingin difilter
     * @param pred => sebuah functional interface untuk mengecek suatu kondisi yang akan digunakan untuk melakukan filter
     * @return list yang berisikan elemen yang memenuhi kondisi dari parameter interface predicate
     */
    public static <T> List<T> collect (Iterator<T> iterator, Predicate<T> pred){
        List<T> list = new ArrayList<>();

        while (iterator.hasNext()) {
            T element = iterator.next();
            if (pred.predicate(element)) {
                list.add(element);
            }
        }
        return list;
    }

    /**
     * Method untuk melakukan perhitungan terhadpa jumlah elemen yang memiliki value sama terhadap parameter
     * @param <T> => jenis class dari suatu objek
     * @param array => array yang berisikan elemen-elemen yang ingin dihitung jumlah dari elemen yang memenuhi kondisi tertentu
     * @param value => nilai value yang akan dibandingkan untuk menghitung elemen yang sesuai dengan value tersebut
     * @return jumlah elemen yang memiliki value yang sama dengan parameter value
     */
    public static <T> int count (T[] array, T value){
        int c = 0;
        Predicate<T> pred = values -> (values == value);
        for (T t : array) {
            if (pred.predicate(t)) {
                c++;
            }
        }
        return c;
    }

    /**
     * Method untuk melakukan perhitungan jumlah elemen dengan value yang sesuai pada paramter
     * dari objek iterable (dapat berupa List, Array, atau Hashmap)
     * @param <T> => jenis class dari suatu objek
     * @param iterable => objek iterable yang berisikan elemen-elemen yang ingin dihitung jumlah dari elemen yang memenuhi kondisi tertentu
     * @param value => nilai value yang akan dibandingkan untuk menghitung elemen yang sesuai dengan value tersebut
     * @return jumlah elemen yang memiliki value yang sama dengan parameter value
     */
    public static <T> int count(Iterable<T> iterable, T value)
    {
        int count = 0;
        Predicate<T> p = value1 -> (value1 == value);
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(p.predicate(element))
            {
                count++;
            }
        }
        return count;
    }

    /**
     * Method untuk menghitung jumlah elemen yang memiliki value yang sama dengan parameter value
     * dari suatu objek iterator dimana iterator biasa digunakan untuk looping dari suatu collection
     * @param <T> => jenis class dari suatu objek
     * @param iterator => suatu objek iterator yang akan me-return elemen-elemen dari suatu collection yang ingin dihitung jumlah dari elemen yang memenuhi kondisi tertentu
     * @param value => nilai value yang akan dibandingkan untuk menghitung elemen yang sesuai dengan value tersebut
     * @return jumlah elemen yang memiliki value yang sama dengan parameter value
     */
    public static <T> int count (Iterator<T> iterator, T value){
        int c = 0;
        Predicate<T> pred = values -> (values == value);
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                c++;
            }
        }
        return c;
    }

    /**
     * Method untuk menghitung jumlah elemen yang memenuhi kondisi dari suatu predicate (interface untuk mengecek suatu kondisi)
     * dari suatu array
     * @param <T> => jenis class dari suatu objek
     * @param array => array yang berisikan elemen-elemen yang ingin dihitung jumlah dari elemen yang memenuhi kondisi tertentu
     * @param pred => sebuah functional interface untuk mengecek suatu kondisi yang akan digunakan untuk menghitung jumlah dari elemen yang memenuhi kondisi dari predicate
     * @return jumlah elemen yang memenuhi kondisi dari parameter interface predicate
     */
    public static <T> int count (T[] array, Predicate<T> pred){
        int c = 0;
        for (T t : array) {
            if (pred.predicate(t)) {
                c++;
            }
        }
        return c;
    }

    /**
     * Method untuk menghitung jumlah elemen yang memenuhi kondisi dari suatu predicate (interface untuk mengecek suatu kondisi)
     * dari suatu elemen Iterable (suatu objek yang dapat di-iterasi seperti list, array, hashmap, dll)
     * @param <T> => jenis class dari suatu objek
     * @param iterable => objek iterable yang berisikan elemen-elemen yang ingin dihitung jumlah dari elemen yang memenuhi kondisi tertentu
     * @param pred => sebuah functional interface untuk mengecek suatu kondisi yang akan digunakan untuk menghitung jumlah dari elemen yang memenuhi kondisi dari predicate
     * @return jumlah elemen yang memenuhi kondisi dari parameter interface predicate
     */
    public static <T> int count(Iterable<T> iterable, Predicate<T> pred)
    {
        int count = 0;
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                count++;
            }
        }
        return count;
    }

    /**
     * Method untuk menghitung jumlah elemen yang memenuhi kondisi dari suatu predicate (interface untuk mengecek suatu kondisi)
     * dari suatu objek iterator dimana iterator biasa digunakan untuk looping dari suatu collection
     * @param <T> => jenis class dari suatu objek
     * @param iterator => suatu objek iterator yang akan me-return elemen-elemen dari suatu collection yang ingin dihitung jumlah dari elemen yang memenuhi kondisi tertentu
     * @param pred => sebuah functional interface untuk mengecek suatu kondisi yang akan digunakan untuk menghitung jumlah dari elemen yang memenuhi kondisi dari predicate
     * @return jumlah elemen yang memenuhi kondisi dari parameter interface predicate
     */
    public static <T> int count (Iterator<T> iterator, Predicate<T> pred){
        int c = 0;
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                c++;
            }
        }
        return c;
    }

    /**
     * Method untuk menentukan terdapatnya sebuah elemen dari sebuah array berdasarkan value yang diberikan
     * @param <T> => jenis class dari suatu objek
     * @param array => array yang berisikan elemen-elemen sebagai collection tempat dicarinya elemen yang diinginkan
     * @param value => nilai value yang akan dibandingkan untuk mencari elemen yang memiliki value yang sama
     * @return true jika terdapat elemen yang memiliki value yang sama dengan parameter value ditemukan, false jika sebaliknya
     */
    public static <T> boolean exists (T[] array, T value){
        Predicate<T> pred = values -> (values == value);
        for (T t : array) {
            if (pred.predicate(t)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method untuk mencari keberadaan dari suatu elemen yang memiliki value yang sama dengan parameter value yang diberikan
     * dari suatu elemen Iterable (suatu objek yang dapat di-iterasi seperti list, array, hashmap, dll)
     * @param <T> => jenis class dari suatu objek
     * @param iterable => objek iterable yang berisikan elemen-elemen sebagai collection tempat dicarinya elemen yang diinginkan
     * @param value => nilai value yang akan dibandingkan untuk mencari elemen yang memiliki value yang sama
     * @return true jika terdapat elemen yang memiliki value yang sama dengan parameter value ditemukan, false jika sebaliknya
     */
    public static <T> boolean exists(Iterable<T> iterable, T value)
    {
        Predicate<T> p = value1 -> (value1 == value);
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(p.predicate(element))
            {
                return true;
            }
        }
        return false;
    }
    /**
     * Method untuk mencari elemen yang memiliki value yang sesuai pada parameter value yang diberikan
     * dari suatu objek iterator (umumnya collection)
     * @param <T> => jenis class dari suatu objek
     * @param iterator => suatu objek iterator yang akan me-return elemen-elemen dari suatu collection yang dijadikan tempat dicarinya elemen yang diinginkan
     * @param value => nilai value yang akan dibandingkan untuk mencari elemen yang memiliki value yang sama
     * @return true jika terdapat elemen yang memiliki value yang sama dengan parameter value ditemukan, false jika sebaliknya
     */
    public static <T> boolean exists (Iterator<T> iterator, T value){
        Predicate<T> pred = values -> (values == value);
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                return true;
            }
        }
        return false;
    }
    /**
     * Method untuk mencari keberadaan dari suatu elemen yang memenuhi kondisi dari suatu predicate
     * yang sudah didefinsikan pada array tersebut
     * @param <T> => jenis class dari suatu objek
     * @param array => array yang berisikan elemen-elemen sebagai collection tempat dicarinya elemen yang diinginkan
     * @param pred => sebuah functional interface untuk mengecek suatu kondisi yang akan digunakan untuk mencari elemen yang memenuhi kondisi dari predicate
     * @return true jika terdapat elemen yang memenuhi kondisi dari parameter predicate ditemukan, false jika sebaliknya
     */
    public static <T> boolean exists (T[] array, Predicate<T> pred){
        for (T t : array) {
            if (pred.predicate(t)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method untuk mencari keberadaan dari suatu elemen yang memenuhi kondisi dari suatu predicate
     * (interface untuk mengecek suatu kondisi) dari suatu elemen Iterable
     * (suatu objek yang dapat di-iterasi seperti list, array, hashmap, dll)
     * @param <T> => jenis class dari suatu objek
     * @param iterable => objek iterable yang berisikan elemen-elemen sebagai collection tempat dicarinya elemen yang diinginkan
     * @param pred => sebuah functional interface untuk mengecek suatu kondisi yang akan digunakan untuk mencari elemen yang memenuhi kondisi dari predicate
     * @return true jika terdapat elemen yang memenuhi kondisi dari parameter predicate ditemukan, false jika sebaliknya
     */
    public static <T> boolean exists (Iterable<T> iterable, Predicate<T> pred){
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                return true;
            }
        }
        return false;
    }

    

    /**
     * Method untuk mencari keberadaan dari suatu elemen yang memenuhi kondisi dari suatu predicate
     * (interface untuk mengecek suatu kondisi) dari suatu objek iterator
     * dimana iterator biasa digunakan untuk looping dari suatu collection
     * @param <T> => jenis class dari suatu objek
     * @param iterator => suatu objek iterator yang akan me-return elemen-elemen dari suatu collection yang dijadikan tempat dicarinya elemen yang diinginkan
     * @param pred => sebuah functional interface untuk mengecek suatu kondisi yang akan digunakan untuk mencari elemen yang memenuhi kondisi dari predicate
     * @return true jika terdapat elemen yang memenuhi kondisi dari parameter predicate ditemukan, false jika sebaliknya
     */
    public static <T> boolean exists(Iterator<T> iterator, Predicate<T> pred)
    {
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                return true;
            }
        }
        return false;
    }
    /**
     * Method untuk mencari elemen yang memiliki nilai sesuai dengan paramter value
     * @param <T> => jenis class dari suatu objek
     * @param array => array yang berisikan elemen-elemen sebagai collection tempat dicarinya elemen yang diinginkan
     * @param value => nilai value yang akan dibandingkan untuk mencari elemen yang memiliki value yang sama
     * @return objek elemen yang memiliki value yang sama dengan parameter value yang diberikan jika ada, null jika elemen tidak ada yang memiliki value yang sama dengan parameter value yang diberikan
     */
    public static <T> T find (T[] array, T value){
        Predicate<T> pred = values -> (values == value);
        for (T t : array) {
            if (pred.predicate(t)) {
                return t;
            }
        }
        return null;
    }

    /**
     * Method untuk mencari elemen yang memiliki nilai sama dengan parameter value
     * dari suatu elemen Iterable (dapat berupa list, array, atau HashMap)
     * @param <T> => jenis class dari suatu objek
     * @param iterable => objek iterable yang berisikan elemen-elemen sebagai collection tempat dicarinya elemen yang diinginkan
     * @param value => nilai value yang akan dibandingkan untuk mencari elemen yang memiliki value yang sama
     * @return objek elemen yang memiliki value yang sama dengan parameter value yang diberikan, null jika elemen tidak ada yang memiliki value yang sama dengan parameter value yang diberikan
     */
    public static <T> T find(Iterable<T> iterable, T value)
    {
        Predicate<T> p = value1 -> (value1 == value);
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(p.predicate(element))
            {
                return element;
            }
        }
        return null;
    }

    /**
     * Method untuk mencari elemen yang memiliki value yang sama dengan parameter value yang diberikan
     * dari suatu objek iterator dimana iterator biasa digunakan untuk looping dari suatu collection
     * @param <T> => jenis class dari suatu objek
     * @param iterator => suatu objek iterator yang akan me-return elemen-elemen dari suatu collection yang dijadikan tempat dicarinya elemen yang diinginkan
     * @param value => nilai value yang akan dibandingkan untuk mencari elemen yang memiliki value yang sama
     * @return objek elemen yang memiliki value yang sama dengan parameter value yang diberikan, null jika elemen tidak ada yang memiliki value yang sama dengan parameter value yang diberikan
     */
    public static <T> T find(Iterator<T> iterator, T value)
    {
        Predicate<T> p = value1 -> (value1 == value);
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(p.predicate(element))
            {
                return element;
            }
        }
        return null;
    }

    /**
     * Method untuk mencari elemen yang memenuhi kondisi dari suatu predicate
     * (interface untuk mengecek suatu kondisi) dari suatu array
     * @param <T> => jenis class dari suatu objek
     * @param array => array yang berisikan elemen-elemen sebagai collection tempat dicarinya elemen yang diinginkan
     * @param pred => sebuah functional interface untuk mengecek suatu kondisi yang akan digunakan untuk mencari elemen yang memenuhi kondisi dari predicate
     * @return objek elemen yang memenuhi kondisi dari suatu predicate, null jika elemen tidak ada yang memenuhi kondisi dari suatu predicate
     */
    public static <T> T find(T[] array, Predicate<T> pred)
    {
        for(int i = 0; i < array.length; i++)
        {
            if(pred.predicate(array[i]))
            {
                return array[i];
            }
        }
        return null;
    }

    /**
     * Method untuk mencari elemen yang memenuhi kondisi dari suatu predicate
     * (interface untuk mengecek suatu kondisi) dari suatu elemen Iterable
     * (suatu objek yang dapat di-iterasi seperti list, array, hashmap, dll)
     * @param <T> => jenis class dari suatu objek
     * @param iterable => objek iterable yang berisikan elemen-elemen sebagai collection tempat dicarinya elemen yang diinginkan
     * @param pred => sebuah functional interface untuk mengecek suatu kondisi yang akan digunakan untuk mencari elemen yang memenuhi kondisi dari predicate
     * @return objek elemen yang memenuhi kondisi dari suatu predicate, null jika elemen tidak ada yang memenuhi kondisi dari suatu predicate
     */
    public static <T> T find(Iterable<T> iterable, Predicate<T> pred)
    {
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                return element;
            }
        }
        return null;
    }

    /**
     * Method untuk mencari elemen yang memenuhi kondisi dari suatu predicate
     * (interface untuk mengecek suatu kondisi) dari suatu objek iterator
     * dimana iterator biasa digunakan untuk looping dari suatu collection
     * @param <T> => jenis class dari suatu objek
     * @param iterator => suatu objek iterator yang akan me-return elemen-elemen dari suatu collection yang dijadikan tempat dicarinya elemen yang diinginkan
     * @param pred => sebuah functional interface untuk mengecek suatu kondisi yang akan digunakan untuk mencari elemen yang memenuhi kondisi dari predicate
     * @return objek elemen yang memenuhi kondisi dari suatu predicate, null jika elemen tidak ada yang memenuhi kondisi dari suatu predicate
     */
    public static <T> T find(Iterator<T> iterator, Predicate<T> pred)
    {
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                return element;
            }
        }
        return null;
    }

    /**
     * Method untuk mencari objek yang memiliki nilai maksimum di antara kedua objek yang diberikan
     * @param <T> => jenis class dari suatu objek
     * @param first => objek pertama yang akan dibandingkan
     * @param second => objek kedua yang akan dibandingkan
     * @return objek yang memiliki nilai terbesar (lebih besar dari objek lain yang dibandingkan)
     */
    public static <T extends Comparable<? super T>> T max(T first, T  second)
    {
        if(first.compareTo(second) > 0)
        {
            return first;
        }
        return second;
    }

    /**
     * Method untuk mencari elemen yang memiliki nilai maksimum di antara seluruh elemen pada array yang diberikan
     * @param <T> => jenis class dari suatu objek
     * @param array => array yang berisikan elemen yang akan dibandingkan untuk dicari objek yang memiliki nilai maksimum
     * @return objek yang memiliki nilai terbesar di antara seluruh objek pada array
     */
    public static <T extends Comparable<? super T>> T max(T[] array){
        T max = null;
        if (array.length == 1) return array[0];
        else {
            for (int i = 1; i < array.length; i++) {
                if (max == null) max = array[i];
                else if (array[i].compareTo(max) > 0) {
                    max = array[i];
                }
            }
            return max;
        }
    }
    /**
     * Method untuk mencari elemen yang memiliki nilai maksimum di antara seluruh elemen pada objek iterable yang diberikan
     * @param <T> => jenis class dari suatu objek
     * @param iterable => objek Iterable yang berisikan elemen yang akan dibandingkan untuk dicari objek yang memiliki nilai maksimum
     * @return objek yang memiliki nilai terbesar di antara seluruh objek pada objek Iterable
     */
    public static <T extends Comparable<? super T>> T max(Iterable<T> iterable){
        Iterator<T> iterator = iterable.iterator();
        T max = null;
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (max == null) max = element;
            else if (element.compareTo(max) > 0) max = element;
        }
        return max;
    }

    

    /**
     * Method untuk mencari elemen yang memiliki nilai maksimum di antara seluruh elemen pada objek Iterator yang diberikan
     * @param <T> => jenis class dari suatu objek
     * @param iterator => objek Iterator yang berisikan elemen yang akan dibandingkan untuk dicari objek yang memiliki nilai maksimum
     * @return objek yang memiliki nilai terbesar di antara seluruh objek pada objek Iterator
     */
    public static <T extends Comparable<? super T>> T max(Iterator<T> iterator)
    {
        T max = iterator.next();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(element.compareTo(max) > 0)
            {
                max = element;
            }
        }
        return max;
    }
    /**
     * Method untuk mencari objek yang memiliki nilai maksimum di antara kedua objek yang diberikan berdasarkan objek Comparator
     * @param <T> => jenis class dari suatu objek
     * @param first => objek pertama yang akan dibandingkan
     * @param second => objek kedua yang akan dibandingkan
     * @param comparator => objek Comparator yang akan membandingkan objek pertama dan objek kedua untuk dicari objek yang memiliki nilai maksimum
     * @return objek yang memiliki nilai terbesar (lebih besar dari objek lain yang dibandingkan)
     */
    public static <T extends Comparable<? super T>> T max(T first, T second, Comparator<? super T> comparator){
        int Compare = comparator.compare(first, second);
        if(Compare > 0)
        {
            return first;
        }
        else
        {
            return second;
        }
    }

    /**
     * Method untuk mencari objek yang memiliki nilai maksimum di antara seluruh elemen pada array yang diberikan berdasarkan objek Comparator
     * @param <T> => jenis class dari suatu objek
     * @param array => array yang berisikan elemen yang akan dibandingkan untuk dicari objek yang memiliki nilai maksimum
     * @param comparator => objek Comparator yang akan membandingkan objek pertama dan objek kedua untuk dicari objek yang memiliki nilai maksimum
     * @return objek yang memiliki nilai terbesar di antara seluruh objek pada array
     */
    public static <T extends Comparable<? super T>> T max(T[] array, Comparator<? super T> comparator){
        T max = array[0];
        for(int i = 0; i < array.length; i++)
        {
            int Compare = comparator.compare(array[i], max);
            if(Compare > 0)
            {
                max = array[i];
            }
        }
        return max;
    }

    /**
     * Method untuk mencari elemen yang memiliki nilai maksimum di antara seluruh elemen pada objek Iterable yang diberikan berdasarkan objek Comparator
     * @param <T> => jenis class dari suatu objek
     * @param iterable => objek Iterable yang berisikan elemen yang akan dibandingkan untuk dicari objek yang memiliki nilai maksimum
     * @param comparator => objek Comparator yang akan membandingkan objek pada objek Iterable untuk dicari objek yang memiliki nilai maksimum
     * @return objek yang memiliki nilai terbesar di antara seluruh objek pada objek Iterable
     */
    public static <T extends Comparable<? super T>> T max(Iterable<T> iterable, Comparator<? super T> comparator){
        Iterator<T> iterator = iterable.iterator();
        T max = iterator.next();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            int Compare = comparator.compare(element, max);
            if(Compare > 0)
            {
                max = element;
            }
        }
        return max;
    }

    /**
     * Method untuk mencari elemen yang memiliki nilai maksimum di antara seluruh elemen pada objek Iterator yang diberikan berdasarkan objek Comparator
     * @param <T> => jenis class dari suatu objek
     * @param iterator => objek Iterator yang berisikan elemen yang akan dibandingkan untuk dicari objek yang memiliki nilai maksimum
     * @param comparator => objek Comparator yang akan membandingkan objek pada objek Iterator untuk dicari objek yang memiliki nilai maksimum
     * @return objek yang memiliki nilai terbesar di antara seluruh objek pada objek Iterator
     */
    public static <T extends Comparable<? super T>> T max(Iterator<T> iterator, Comparator<? super T> comparator){
        T max = iterator.next();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            int Compare = comparator.compare(element, max);
            if(Compare > 0)
            {
                max = element;
            }
        }
        return max;
    }

    /**
     * Method untuk mencari objek yang memiliki nilai minimum di antara kedua objek yang diberikan
     * @param <T> => jenis class dari suatu objek
     * @param first objek pertama yang akan dibandingkan
     * @param second => objek kedua yang akan dibandingkan
     * @return objek yang memiliki nilai terkecil (lebih kecil dari objek lain yang dibandingkan)
     */
    public static <T extends Comparable<? super T>> T min(T  first, T  second)
    {
        if(first.compareTo(second) < 0)
        {
            return first;
        }
        return second;
    }

    /**
     * Method untuk mencari objek yang memiliki nilai minimum di antara seluruh elemen pada array yang diberikan
     * @param <T> => jenis class dari suatu objek
     * @param array => array yang berisikan elemen yang akan dibandingkan untuk dicari objek yang memiliki nilai minimum
     * @return objek yang memiliki nilai terkecil di antara seluruh objek pada array
     */
    public static <T extends Comparable<? super T>> T min(T[] array)
    {
        T min = array[0];
        for(int i = 0; i < array.length; i++)
        {
            if(array[i].compareTo(min) < 0)
            {
                min = array[i];
            }
        }
        return min;
    }
    
    /**
     * Method untuk mencari elemen yang memiliki nilai minimum di antara seluruh elemen pada objek Iterable yang diberikan
     * @param <T> => jenis class dari suatu objek
     * @param iterable => objek Iterable yang berisikan elemen yang akan dibandingkan untuk dicari objek yang memiliki nilai minimum
     * @return objek yang memiliki nilai terkecil di antara seluruh objek pada objek Iterable
     */
    public static <T extends Comparable<? super T>> T min(Iterable<T> iterable)
    {
        Iterator<T> iterator = iterable.iterator();
        T min = iterator.next();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(element.compareTo(min) < 0)
            {
                min = element;
            }
        }
        return min;
    }

    /**
     * Method untuk mencari elemen yang memiliki nilai minimum di antara seluruh elemen pada objek Iterator yang diberikan
     * @param <T> => jenis class dari suatu objek
     * @param iterator => objek Iterator yang berisikan elemen yang akan dibandingkan untuk dicari objek yang memiliki nilai minimum
     * @return objek yang memiliki nilai terkecil di antara seluruh objek pada objek Iterator
     */
    public static <T extends Comparable<? super T>> T min(Iterator<T> iterator)
    {
        T min = iterator.next();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(element.compareTo(min) < 0)
            {
                min = element;
            }
        }
        return min;
    }
    /**
     * Method untuk mencari objek yang memiliki nilai minimum di antara kedua objek yang diberikan berdasarkan objek Comparator
     * @param <T> => jenis class dari suatu objek
     * @param first => objek pertama yang akan dibandingkan
     * @param second => objek kedua yang akan dibandingkan
     * @param comparator => objek Comparator yang akan membandingkan objek pertama dan objek kedua untuk dicari objek yang memiliki nilai minimum
     * @return objek yang memiliki nilai terkecil (lebih kecil dari objek lain yang dibandingkan)
     */
    public static <T extends Comparable<? super T>> T min(T first, T second, Comparator<? super T> comparator){
        int Compare = comparator.compare(first, second);
        if(Compare < 0)
        {
            return first;
        }
        else
        {
            return second;
        }
    }

    /**
     * Method untuk mencari objek yang memiliki nilai minimum di antara seluruh elemen pada array yang diberikan berdasarkan objek Comparator
     * @param <T> => jenis class dari suatu objek
     * @param array => array yang berisikan elemen yang akan dibandingkan untuk dicari objek yang memiliki nilai minimum
     * @param comparator => objek Comparator yang akan membandingkan objek pada array untuk dicari objek yang memiliki nilai minimum
     * @return objek yang memiliki nilai terkecil di antara seluruh objek pada array
     */
    public static <T extends Comparable<? super T>> T min(T[] array, Comparator<? super T> comparator){
        T min = array[0];
        for(int i = 0; i < array.length; i++)
        {
            int Compare = comparator.compare(array[i], min);
            if(Compare < 0)
            {
                min = array[i];
            }
        }
        return min;
    }

    /**
     * Method untuk mencari elemen yang memiliki nilai minimum di antara seluruh elemen pada objek Iterable yang diberikan berdasarkan objek Comparator
     * @param <T> => jenis class dari suatu objek
     * @param iterable => objek Iterable yang berisikan elemen yang akan dibandingkan untuk dicari objek yang memiliki nilai minimum
     * @param comparator => objek Comparator yang akan membandingkan objek pada objek Iterable untuk dicari objek yang memiliki nilai minimum
     * @return objek yang memiliki nilai terkecil di antara seluruh objek pada objek Iterable
     */
    public static <T extends Comparable<? super T>> T min(Iterable<T> iterable, Comparator<? super T> comparator){
        Iterator<T> iterator = iterable.iterator();
        T min = iterator.next();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            int Compare = comparator.compare(element, min);
            if(Compare < 0)
            {
                min = element;
            }
        }
        return min;
    }

    /**
     * Method untuk mencari elemen yang memiliki nilai minimum di antara seluruh elemen pada objek Iterator yang diberikan berdasarkan objek Comparator
     * @param <T> => jenis class dari suatu objek
     * @param iterator => objek Iterator yang berisikan elemen yang akan dibandingkan untuk dicari objek yang memiliki nilai minimum
     * @param comparator => objek Comparator yang akan membandingkan objek pada objek Iterator untuk dicari objek yang memiliki nilai minimum
     * @return objek yang memiliki nilai terkecil di antara seluruh objek pada objek Iterator
     */
    public static <T extends Comparable<? super T>> T min(Iterator<T> iterator, Comparator<? super T> comparator){
        T min = iterator.next();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            int Compare = comparator.compare(element, min);
            if(Compare < 0)
            {
                min = element;
            }
        }
        return min;
    }

    /**
     * Method untuk melakukan filter dan paginasi pada array yang diberikan berdasarkan parameter predicate yang diberikan
     * @param <T> => jenis class dari suatu objek
     * @param array => array yang akan difilter dan dipaginasi
     * @param page => menunjukkan indeks page ke berapa
     * @param pageSize => ukuran page (jumlah elemen yang dapat dikandung dalam 1 page)
     * @param pred => sebuah functional interface untuk mengecek suatu kondisi yang akan digunakan untuk melakukan filter pada array
     * @return list yang berisi objek dari array yang sudah di-filter dan dilakukan paginasi sesuai parameter yang diberikan
     */
    public static <T> List<T> paginate(T[] array, int page, int pageSize, Predicate<T> pred){
        List<T> list = new ArrayList<>();
        for(T obj : array)
        {
            if(pred.predicate(obj))
            {
                list.add(obj);
            }
        }
        int startIndex = page * pageSize;
        int endIndex = startIndex + pageSize;
        List<T> paginatedList;
        if(endIndex > list.size())
        {
            paginatedList = list.subList(startIndex, list.size());
        }
        else
        {
            paginatedList = list.subList(startIndex, endIndex);
        }
        return paginatedList;
    }

    /**
     * Method untuk melakukan filter dan paginasi pada objek Iterable yang diberikan berdasarkan parameter predicate yang diberikan
     * @param <T> => jenis class dari suatu objek
     * @param iterable => objek Iterable yang akan difilter dan dipaginasi
     * @param page => menunjukkan indeks page ke berapa
     * @param pageSize => ukuran page (jumlah elemen yang dapat dikandung dalam 1 page)
     * @param pred => sebuah functional interface untuk mengecek suatu kondisi yang akan digunakan untuk melakukan filter pada objek Iterable
     * @return list yang berisi objek dari objek Iterable yang sudah di-filter dan dilakukan paginasi sesuai parameter yang diberikan
     */
    public static <T> List<T> paginate(Iterable<T> iterable, int page, int pageSize, Predicate<T> pred){
        List<T> list = new ArrayList<>();
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                list.add(element);
            }
        }
        int startIndex = page * pageSize;
        int endIndex = startIndex + pageSize;
        List<T> paginatedList;
        if(endIndex > list.size())
        {
            paginatedList = list.subList(startIndex, list.size());
        }
        else
        {
            paginatedList = list.subList(startIndex, endIndex);
        }
        return paginatedList;
    }

    /**
     * Method untuk melakukan filter dan paginasi pada objek Iterator yang diberikan berdasarkan parameter predicate yang diberikan
     * @param <T> => jenis class dari suatu objek
     * @param iterator => objek Iterator yang me-return elemen-elemen dari suatu collection yang akan difilter dan dipaginasi
     * @param page => menunjukkan indeks page ke berapa
     * @param pageSize => ukuran page (jumlah elemen yang dapat dikandung dalam 1 page)
     * @param pred => sebuah functional interface untuk mengecek suatu kondisi yang akan digunakan untuk melakukan filter pada objek Iterator
     * @return list yang berisi objek dari objek Iterator yang sudah di-filter dan dilakukan paginasi sesuai parameter yang diberikan
     */
    public static <T> List<T> paginate(Iterator<T> iterator, int page, int pageSize, Predicate<T> pred){
        List<T> list = new ArrayList<>();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                list.add(element);
            }
        }
        int startIndex = page * pageSize;
        int endIndex = startIndex + pageSize;
        List<T> paginatedList;
        if(endIndex > list.size())
        {
            paginatedList = list.subList(startIndex, list.size());
        }
        else
        {
            paginatedList = list.subList(startIndex, endIndex);
        }
        return paginatedList;
    }

}