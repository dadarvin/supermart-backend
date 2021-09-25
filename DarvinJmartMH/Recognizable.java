package DarvinJmartMH;


/**
 * Write a description of class Recognizable here.
 *
 * @author Darvin
 * @version Modul 3
 */
public class Recognizable
{
    final public int id;
    
    protected Recognizable(int id){
        this.id = id;
    }
    
    public boolean equals(Object object){
        if (object instanceof Recognizable){
            Recognizable rec = (Recognizable)object;
            return (this.id == rec.id);
        } else {
            return false;
        }
    }
    
    public boolean equals(Recognizable rec){
        return (this.id == rec.id);
    }
    
}
