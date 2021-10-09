package DarvinJmartMH;

/**
 * Write a description of class Recognizable here.
 *
 * @author Darvin
 * @version Modul 3
 */
public class Recognizable implements Comparable<Recognizable>
{
    final public int id;
    
    /**
     * @param id untuk memberikan id product
     */
    protected Recognizable(int id){
        this.id = id;
    }

    @Override
    public int compareTo(Recognizable other){
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
        if (other instanceof Recognizable){
            Recognizable rec = (Recognizable)other;
            return (this.id == rec.id);
        } else {
            return false;
        }
    }
    
    /**
     * @return memberiakn true jika id sama dengan objek Recognizable
     */
    public boolean equals(Recognizable other){
        return (this.id == other.id);
    }

    public static <T extends Recognizable> int setClosingId(Class<T> clazz, int id){
        return 0;
    }

    public static <T extends Recognizable> int getClosingId(Class<T> clazz){
        return 0;
    }
    
}
