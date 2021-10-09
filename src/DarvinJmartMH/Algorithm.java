package DarvinJmartMH;

import java.util.Iterator;

public class Algorithm{

    private Algorithm(){

    }

    public<T> int count(T[] array, T value){
        Predicate<T> prd = val -> (val == value);
        int cnt = 0;
        for(int i = 0; i < array.length; i++){
            if(prd.predicate(array[i])){
                cnt++;
            }
        }
        return cnt;
    }

    public<T> int count(Iterable<T> iterable, T value){
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

    public<T> int count(Iterator<T> iterator, T value){
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

    public<T> int count(T[] array, Predicate<T> pred){
        int cnt = 0;

        for (T t : array) {
            if (pred.predicate(t)) {
                cnt++;
            }
        }
        return cnt;
    }

    public<T> int count(Iterable<T> iterable, Predicate<T> pred){
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

    public<T> int count(Iterator<T> iterator, Predicate<T> pred){
        int cnt = 0;

        while(iterator.hasNext()){
            T element = iterator.next();
            if(pred.predicate(element)){
                cnt++;
            }
        }
        return cnt;
    }

    public<T> boolean exists(T[] array, T value){
        Predicate<T> p = val -> (val == value);
        for (T theArray : array) {
            if (p.predicate(theArray)) {
                return true;
            }
        }
        return false;
    }

    public<T> boolean exists (Iterable<T> iterable, T value){
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

    public<T> boolean exists (Iterator<T> iterator, T value){
        Predicate<T> p = val -> (val == value);
        while(iterator.hasNext()){
            T element = iterator.next();
            if(p.predicate(element)){
                return true;
            }
        }
        return false;
    }

    public<T> boolean exists(T[] array, Predicate<T> pred){
        for (T t : array) {
            if (pred.predicate(t)) {
                return true;
            }
        }
        return false;
    }

    public<T> boolean exists(Iterable<T> iterable, Predicate<T> pred){
        Iterator<T> iterator = iterable.iterator();

        while(iterator.hasNext()){
            T element = iterator.next();
            if(pred.predicate(element)){
                return true;
            }
        }
        return false;
    }

    public<T> boolean exists(Iterator<T> iterator, Predicate<T> pred){
        while(iterator.hasNext()){
            T element = iterator.next();
            if(pred.predicate(element)){
                return true;
            }
        }
        return false;
    }

    public<T> T find(T[] array, T value){
        Predicate<T> p = val -> (val == value);

        for (T theArray : array) {
            if (p.predicate(theArray)) {
                return theArray;
            }
        }
        return null;
    }

    public<T> T find(Iterable<T> iterable, T value){
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

    public<T> T find(Iterator<T> iterator, T value){
        Predicate<T> p = val -> (val == value);
        while(iterator.hasNext()){
            T element = iterator.next();
            if(p.predicate(element)){
                return element;
            }
        }
        return null;
    }

    public<T> T find(T[] array, Predicate<T> pred){
        for (T theArray : array) {
            if (pred.predicate(theArray)) {
                return theArray;
            }
        }
        return null;
    }

    public<T> T find(Iterable<T> iterable, Predicate<T> pred){
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

    public<T> T find(Iterator<T> iterator, Predicate<T> pred){
        while(iterator.hasNext()){
            T element = iterator.next();
            if(pred.predicate(element))
            {
                return element;
            }
        }
        return null;
    }

    public <T extends Comparable<? super T>> T max(T first, T  second){
        if(first.compareTo(second) > 0){
            return first;
        } else {
            return second;
        }
    }

    public <T extends Comparable<? super T>> T min(T  first, T  second){
        if(first.compareTo(second) < 0){
            return first;
        } else {
            return second;
        }
    }
}
