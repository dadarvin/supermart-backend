package DarvinJmartMH;


/**
 * Write a description of class FileParser here.
 *
 * @author Darvin
 * @version Modul 3
 */
public interface FileParser
{
    public boolean read(String content);
    
    default Object write(){
        return null;
    }
    
    public static Object newInstance(String content){
        return null;
    };
}
