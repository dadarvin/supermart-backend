package DarvinJmartMH;

import java.util.Vector;
import java.util.function.Function;

public class ObjectPoolThread<T> extends Thread{
    private boolean exitSignal;
    private Vector<T> objectPool;
    private Function<T, Boolean> routine;

    public ObjectPoolThread(String name, Function<T, Boolean> routine) {
        super(name);
        this.routine = routine;
    }

    public ObjectPoolThread(Function<T, Boolean> routine) {
        this.routine = routine;
    }

    public synchronized void add(T object){
        objectPool.add(object);
        this.notify();
    }

    public synchronized void exit(){
        exitSignal = true;
    }

    public void run(){
//        synchronized (this){
//            while(!exitSignal){
//                try {
//                    while(objectPool.size() != 0){
//                        for(T t : objectPool){
//                            if(routine.apply(t)){
//                                objectPool.remove(t);
//                            }
//                        }
//                        this.wait();
//                    }
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }
    }

    public int size(){
        return objectPool.size();
    }
}
