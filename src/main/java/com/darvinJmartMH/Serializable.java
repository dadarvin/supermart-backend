package com.darvinJmartMH;

import java.util.HashMap;
import java.util.Map;

/**
 * Write a description of class Recognizable here.
 *
 * @author Darvin
 * @version Modul 3
 */
public class Serializable implements Comparable<Serializable>
{
    final public int id;
    static Map<Class<?>, Integer> mapCounter = new HashMap<Class<?>, Integer>();

    protected Serializable(){
        Class gc = getClass();
        if(mapCounter.get(gc) == null){
            mapCounter.put(gc, 0);
        } else {
            mapCounter.put(gc, mapCounter.get(gc)+1 );
        }
        this.id = mapCounter.get(gc);
    }

    @Override
    public int compareTo(Serializable other){
        if(id == other.id){
            return 1;
        } else {
            return 0;
        }
    }
    
    /**
     * @return bergantung pada hasil komparasi id
     */
    public boolean equals(Object other){
        if (other instanceof Serializable){
            Serializable rec = (Serializable)other;
            return (this.id == rec.id);
        } else {
            return false;
        }
    }
    
    /**
     * @return memberiakn true jika id sama dengan objek Serializable
     */
    public boolean equals(Serializable other){
        return (this.id == other.id);
    }

    public static <T extends Serializable> int setClosingId(Class<T> clazz, int id){
        return mapCounter.put(clazz, id);
    }

    public static <T extends Serializable> int getClosingId(Class<T> clazz){
        return mapCounter.get(clazz);
    }
    
}
