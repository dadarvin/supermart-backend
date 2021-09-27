package DarvinJmartMH;


/**
 * Write a description of class Complaint here.
 *
 * @author Darvin
 * @version Modul 3
 */
public class Complaint extends Recognizable implements FileParser
{
    public String date;
    public String desc;
    
    /*
     * @param id berasal dari parent class
     * @param desc menjelaskan keterangan complaint
     */
    public Complaint(int id, String desc){
        super(id);
        this.desc = desc;
        this.date = "27/09/2020";
    }
    
    public boolean read(String content){
        return false;
    }
}
