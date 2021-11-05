package DarvinJmartMH;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Algorithm{

    private Algorithm(){

    }

    public static<T> List<T> collect (T[] array, T value){
        Predicate<T> prd = val -> val.equals(value);
        List<T> list = new ArrayList<>();

        for (T currentArray : array) {
            if (prd.predicate(currentArray)) {
                list.add(currentArray);
            }
        }

        return list;
    }

    public static<T> List<T> collect (Iterable<T> iterable, T value){
        Predicate<T> prd = val -> val.equals(value);
        List<T> list = new ArrayList<>();
        Iterator<T> iterator = iterable.iterator();

        while (iterator.hasNext()) {
            T element = iterator.next();
            if (prd.predicate(element)) {
                list.add(element);
            }
        }

        return list;
    }

    public static<T> List<T> collect (Iterator<T> iterator, T value){
        Predicate<T> prd = val -> val.equals(value);
        List<T> list = new ArrayList<>();

        while (iterator.hasNext()) {
            T element = iterator.next();
            if (prd.predicate(element)) {
                list.add(element);
            }
        }

        return list;
    }

    public static<T> List<T> collect (T[] array, Predicate<T> pred){
        List<T> list = new ArrayList<>();

        for (T currentArray : array) {
            if (pred.predicate(currentArray)) {
                list.add(currentArray);
            }
        }

        return list;
    }

    public static<T> List<T> collect (Iterable<T> iterable, Predicate<T> pred){
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

    public static<T> List<T> collect (Iterator<T> iterator, Predicate<T> pred){
        List<T> list = new ArrayList<>();

        while (iterator.hasNext()){
            T element = iterator.next();
            if(pred.predicate(element)){
                list.add(element);
            }
        }

        return list;
    }

    public static<T> int count(T[] array, T value){
        Predicate<T> prd = val -> (val == value);
        int cnt = 0;
        for(int i = 0; i < array.length; i++){
            if(prd.predicate(array[i])){
                cnt++;
            }
        }

        return cnt;
    }

    public static<T> int count(Iterable<T> iterable, T value){
        Predicate<T> prd = val -> (val == value);
        Iterator<T> iterator = iterable.iterator();

        int cnt = 0;
        while(iterator.hasNext()){
            T element = iterator.next();
            if(prd.predicate(element)){
                cnt++;
            }
        }

        return cnt;
    }

    public static<T> int count(Iterator<T> iterator, T value){
        Predicate<T> prd = val -> (val == value);

        int cnt = 0;
        while(iterator.hasNext()){
            T element = iterator.next();
            if(prd.predicate(element)){
                cnt++;
            }
        }

        return cnt;
    }

    public static<T> int count(T[] array, Predicate<T> pred){
        int cnt = 0;

        for (T currentArray : array) {
            if (pred.predicate(currentArray)) {
                cnt++;
            }
        }

        return cnt;
    }

    public static<T> int count(Iterable<T> iterable, Predicate<T> pred){
        int cnt = 0;

        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext()){
            T element = iterator.next();
            if(pred.predicate(element)){
                cnt++;
            }
        }

        return cnt;
    }

    public static<T> int count(Iterator<T> iterator, Predicate<T> pred){
        int cnt = 0;

        while(iterator.hasNext()){
            T element = iterator.next();
            if(pred.predicate(element)){
                cnt++;
            }
        }

        return cnt;
    }

    public static<T> boolean exists(T[] array, T value){
        Predicate<T> p = val -> (val == value);
        for (T currentArray : array) {
            if (p.predicate(currentArray)) {
                return true;
            }
        }

        return false;
    }

    public static<T> boolean exists (Iterable<T> iterable, T value){
        Predicate<T> p = val -> (val == value);
        Iterator<T> iterator = iterable.iterator();

        while(iterator.hasNext()){
            T element = iterator.next();
            if(p.predicate(element)){
                return true;
            }
        }

        return false;
    }

    public static<T> boolean exists (Iterator<T> iterator, T value){
        Predicate<T> p = val -> (val == value);

        while(iterator.hasNext()){
            T element = iterator.next();
            if(p.predicate(element)){
                return true;
            }
        }

        return false;
    }

    public static<T> boolean exists(T[] array, Predicate<T> pred){
        for (T currentArray : array) {
            if (pred.predicate(currentArray)) {
                return true;
            }
        }

        return false;
    }

    public static<T> boolean exists(Iterable<T> iterable, Predicate<T> pred){
        Iterator<T> iterator = iterable.iterator();

        while(iterator.hasNext()){
            T element = iterator.next();
            if(pred.predicate(element)){
                return true;
            }
        }

        return false;
    }

    public static<T> boolean exists(Iterator<T> iterator, Predicate<T> pred){
        while(iterator.hasNext()){
            T element = iterator.next();
            if(pred.predicate(element)){
                return true;
            }
        }

        return false;
    }

    public static<T> T find(T[] array, T value){
        Predicate<T> p = val -> (val == value);

        for (T currentArray : array) {
            if (p.predicate(currentArray)) {
                return currentArray;
            }
        }

        return null;
    }

    public static<T> T find(Iterable<T> iterable, T value){
        Predicate<T> p = val -> (val == value);
        Iterator<T> iterator = iterable.iterator();

        while(iterator.hasNext()){
            T element = iterator.next();
            if(p.predicate(element)){
                return element;
            }
        }

        return null;
    }

    public static<T> T find(Iterator<T> iterator, T value){
        Predicate<T> p = val -> (val == value);
        while(iterator.hasNext()){
            T element = iterator.next();
            if(p.predicate(element)){
                return element;
            }
        }

        return null;
    }

    public static<T> T find(T[] array, Predicate<T> pred){
        for (T currentArray : array) {
            if (pred.predicate(currentArray)) {
                return currentArray;
            }
        }

        return null;
    }

    public static<T> T find(Iterable<T> iterable, Predicate<T> pred){
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext()){
            T element = iterator.next();
            if(pred.predicate(element))
            {
                return element;
            }
        }

        return null;
    }

    public static<T> T find(Iterator<T> iterator, Predicate<T> pred){
        while(iterator.hasNext()){
            T element = iterator.next();
            if(pred.predicate(element))
            {
                return element;
            }
        }

        return null;
    }

    public static<T extends Comparable<? super T>> T max(T first, T  second){
        if(first.compareTo(second) > 0){
            return first;
        } else {
            return second;
        }
    }

    public static<T extends Comparable<? super T>> T max(T[] array){
        /*
        @maxVal sebagai nilai awal perbandingan nilai terbesar
         */
        T maxVal = array[0];

        for(T currentArray: array){
            if(currentArray.compareTo(maxVal) > 0){
                maxVal = currentArray;
            }
        }

        return maxVal;
    }

    public static<T extends Comparable<? super T>> T max(Iterable<T> iterable){
        Iterator<T> iterator = iterable.iterator();
        T maxVal = iterator.next();

        while(iterator.hasNext()){
            T element = iterator.next();
            if(element.compareTo(maxVal) > 0){
                maxVal = element;
            }
        }

        return maxVal;
    }

    public static<T extends Comparable<? super T>> T max(Iterator<T> iterator){
        T maxVal = iterator.next();

        while(iterator.hasNext()){
            T element = iterator.next();
            if(!(element.compareTo(maxVal) <= 0)){
                maxVal = element;
            }
        }

        return maxVal;
    }

    public static<T extends Comparable<? super T>> T max(T first, T second, Comparator<? super T> comparator){
        int compareVal = comparator.compare(first, second);

        if(compareVal > 0){
            return first;
        } else{
            return  second;
        }

    }

    public static<T extends Comparable<? super T>> T max(T[] array, Comparator<? super T> comparator){
        T maxVal = array[0];

        for (T currentArray: array){
            int compareVal = comparator.compare(currentArray, maxVal);
            if(compareVal > 0){
                maxVal = currentArray;
            }
        }

        return maxVal;
    }

    public static<T extends Comparable<? super T>> T max(Iterable<T> iterable, Comparator<? super T> comparator){
        Iterator<T> iterator = iterable.iterator();
        T maxVal = iterator.next();

        while(iterator.hasNext()){
            T element = iterator.next();
            int compareVal = comparator.compare(element, maxVal);
            if(compareVal > 0){
                maxVal = element;
            }
        }

        return maxVal;
    }

    public static<T extends Comparable<? super T>> T max(Iterator<T> iterator, Comparator<? super T> comparator){
        T maxVal = iterator.next();

        while(iterator.hasNext()){
            T element = iterator.next();
            int compareVal = comparator.compare(element, maxVal);
            if(compareVal > 0){
                maxVal = element;
            }
        }

        return maxVal;
    }

    public static<T extends Comparable<? super T>> T min(T  first, T  second){
        if(first.compareTo(second) < 0){
            return first;
        } else {
            return second;
        }
    }

    public static<T extends Comparable<? super T>> T min(T[] array){
        T minVal = array[0];

        for(T currentArray: array){
            if(currentArray.compareTo(minVal) < 0){
                minVal = currentArray;
            }
        }

        return minVal;
    }

    public static<T extends Comparable<? super T>> T min(Iterable<T> iterable){
        Iterator<T> iterator = iterable.iterator();
        T minVal = iterator.next();

        while(iterator.hasNext()){
            T element = iterator.next();
            if(element.compareTo(minVal) < 0){
                minVal = element;
            }
        }

        return minVal;
    }

    public static<T extends Comparable<? super T>> T min(Iterator<T> iterator){
        T minVal = iterator.next();

        while(iterator.hasNext()){
            T element = iterator.next();
            if(element.compareTo(minVal) < 0){
                minVal = element;
            }
        }

        return minVal;
    }

    public static<T extends Comparable<? super T>> T min(T first, T second, Comparator<? super T> comparator){
        int compareVal = comparator.compare(first, second);

        if(compareVal < 0){
            return first;
        } else{
            return  second;
        }

    }

    public static<T extends Comparable<? super T>> T min(T[] array, Comparator<? super T> comparator){
        T minVal = array[0];

        for (T currentArray: array){
            int compareVal = comparator.compare(currentArray, minVal);
            if(compareVal < 0){
                minVal = currentArray;
            }
        }

        return minVal;
    }

    public static<T extends Comparable<? super T>> T min(Iterable<T> iterable, Comparator<? super T> comparator){
        Iterator<T> iterator = iterable.iterator();
        T minVal = iterator.next();

        while(iterator.hasNext()){
            T element = iterator.next();
            int compareVal = comparator.compare(element, minVal);
            if(compareVal < 0){
                minVal = element;
            }
        }

        return minVal;
    }

    public static<T extends Comparable<? super T>> T min(Iterator<T> iterator, Comparator<? super T> comparator){
        T minVal = iterator.next();

        while(iterator.hasNext()){
            T element = iterator.next();
            int compareVal = comparator.compare(element, minVal);
            if(compareVal < 0){
                minVal = element;
            }
        }

        return minVal;
    }

}
