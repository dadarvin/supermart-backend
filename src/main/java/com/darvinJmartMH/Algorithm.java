package com.darvinJmartMH;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Algorithm {
    private Algorithm(){
    }

    public static <T> List<T> collect (T[] array, T value){
        Predicate<T> prd = val -> val.equals(value);
        List<T> list = new ArrayList<>();
        for (T element : array) {
            if (prd.predicate(element)) {
                list.add(element);
            }
        }
        return list;
    }

    public static <T> List<T> collect (Iterable<T> iterable, T value){
        Predicate<T> pred = val -> val.equals(value);
        List<T> list = new ArrayList<>();
        Iterator<T> iterator = iterable.iterator();

        while (iterator.hasNext()) {
            T element = iterator.next();
            if (pred.predicate(element)) {
                list.add(element);
            }
        }
        return list;
    }

    public static<T> List<T> collect(Iterator<T> iterator, T value) {
        List<T> list = new ArrayList<T>();
        while(iterator.hasNext()) {
            T t = iterator.next();
            if(t.equals(value)) {
                list.add(t);
            }
        }
        return list;
    }

    public static <T> List<T> collect (T[] array, Predicate<T> pred){
        List<T> list = new ArrayList<>();
        for (T element : array) {
            if (pred.predicate(element)) {
                list.add(element);
            }
        }
        return list;
    }

    public static <T> List<T> collect (Iterable<T>iterable, Predicate<T> pred){
        List<T> list = new ArrayList<>();
        Iterator<T> iterator = iterable.iterator();

        while (iterator.hasNext()) {
            T element = iterator.next();
            if (pred.predicate(element)) {
                list.add(element);
            }
        }
        return list;
    }

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

    public static <T> int count (Iterable<T> iterable, T value){
        int c = 0;
        Predicate<T> pred = values -> (values == value);
        for (T element : iterable) {
            if (pred.predicate(element)) {
                c++;
            }
        }
        return c;
    }

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

    public static <T> int count (T[] array, Predicate<T> pred){
        int c = 0;
        for (T t : array) {
            if (pred.predicate(t)) {
                c++;
            }
        }
        return c;
    }

    public static <T> int count (Iterable<T> iterable, Predicate<T> pred){
        int c = 0;
        for (T element : iterable) {
            if (pred.predicate(element)) {
                c++;
            }
        }
        return c;
    }

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


    public static <T> boolean exists (T[] array, T value){
        Predicate<T> pred = values -> (values == value);
        for (T t : array) {
            if (pred.predicate(t)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean exists (Iterable<T> iterable, T value){
        Predicate<T> pred = values -> (values == value);
        for (T element : iterable) {
            if (pred.predicate(element)) {
                return true;
            }
        }
        return false;
    }
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
    public static <T> boolean exists (T[] array, Predicate<T> pred){
        for (T t : array) {
            if (pred.predicate(t)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean exists (Iterable<T> iterable, Predicate<T> pred){
        for (T element : iterable) {
            if (pred.predicate(element)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean exists (Iterator<T> iterator, Predicate<T> pred){
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

    public static <T> T find (T[] array, T value){
        Predicate<T> pred = values -> (values == value);
        for (T t : array) {
            if (pred.predicate(t)) {
                return t;
            }
        }
        return null;
    }

    public static <T> T find  (Iterable<T> iterable, T value){
        Predicate<T> pred = values -> (values == value);
        for (T element : iterable) {
            if (pred.predicate(element)) {
                return element;
            }
        }
        return null;
    }

    public static <T> T find (Iterator<T> iterator, T value){
        Predicate<T> pred = values -> (values == value);
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

    public static <T> T find  (T[] array, Predicate<T> pred){
        for (T t : array) {
            if (pred.predicate(t)) {
                return t;
            }
        }
        return null;
    }

    public static <T> T find  (Iterable<T> iterable, Predicate<T> pred){
        for (T element : iterable) {
            if (pred.predicate(element)) {
                return element;
            }
        }
        return null;
    }

    public static <T> T find  (Iterator<T> iterator, Predicate<T> pred){
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

    public static <T extends Comparable<? super T>> T max(T first, T  second)
    {
        if(first.compareTo(second) > 0)
        {
            return first;
        }
        return second;
    }

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

    public static <T extends Comparable<? super T>> T max(Iterator<T> iterator){
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

    public static <T extends Comparable<? super T>> T min(T  first, T  second)
    {
        if(first.compareTo(second) < 0)
        {
            return first;
        }
        return second;
    }

    public static <T extends Comparable<? super T>> T min(T[] array){
        T min = null;
        if (array.length == 1) return array[0];
        else {
            for (int i = 1; i < array.length; i++) {
                if (min == null) min = array[i];
                else if (array[i].compareTo(min) < 0) {
                    min = array[i];
                }
            }
            return min;
        }
    }

    public static <T extends Comparable<? super T>> T min(Iterable<T> iterable){
        Iterator<T> iterator = iterable.iterator();
        T min = null;
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (min == null) min = element;
            else if (element.compareTo(min) < 0) min = element;
        }
        return min;
    }

    public static <T extends Comparable<? super T>> T min(Iterator<T> iterator){
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