package com.darvinJmartMH;

import java.util.Vector;
import java.util.function.Function;

/**
 * Class ObjectPoolThread yang menjalankan proses multithtreading pada payment
 * @param <T> jenis class
 */
public class ObjectPoolThread<T> extends Thread{
    private boolean exitSignal;
    private Vector<T> objectPool = new Vector<T>();
    private Function<T, Boolean> routine;

    /**
     * Constructor untuk instansiasi ObjectPoolThread
     * @param name nama dari thread
     * @param routine method yang dijadikan pada routine
     */
    public ObjectPoolThread(String name, Function<T, Boolean> routine){
        super(name);
        this.routine = routine;
    }

    /**
     * Constructor untuk mengisntansiasi ObjectPoolThread
     * @param routine method yang akan dijadikan routine
     */
    public ObjectPoolThread(Function<T, Boolean> routine){
        super();
        this.routine = routine;
    }

    /**
     * Method untuk mengembalikan jumlah thread yang sedang dijalankan
     * @return jumlah dari thread yang berjalan
     */
    public int size(){
        return objectPool.size();
    }

    /**
     * Method untuk menambahbahkan objek pada thread dengan sinkronisasi agar tidak terjadi racing
     * @param object object yang ingin ditambahkan
     */
    public synchronized void add(T object){
        objectPool.add(object);
        this.notify();
    }

    /**
     * Method untuk keluar dari loop run pada thread agar thread mati
     */
    public synchronized void exit(){
        exitSignal = true;
        this.notify();
    }

    /**
     * Method yang akan dijalankan pada setiap thread ketika dalam kondisi running
     */
    public void run()
    {
        while(true)
        {
            for(int i = 0; i < objectPool.size(); i++)
            {
                T object = objectPool.get(i);
                if (routine.apply(object))      
                {
                    objectPool.remove(i);       
                    --i;
                }
            }
            synchronized (this)                    
            {
                while (objectPool.isEmpty() && !exitSignal)         
                {
                    try { this.wait(); }                            
                    catch (InterruptedException e) {}
                }
                if(exitSignal) {                        
                    break;
                }
            }
        }
    }
}
