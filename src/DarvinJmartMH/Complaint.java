package DarvinJmartMH;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Write a description of class Complaint here.
 *
 * @author Darvin
 * @version Modul 3
 */
public class Complaint extends Recognizable implements FileParser
{
    public Date date;
    public String desc;
    
    /*
     * @param id berasal dari parent class
     * @param desc menjelaskan keterangan complaint
     */
    public Complaint(int id, String desc){
        super(id);
        this.desc = desc;
        this.date = new Date();
    }
    
    public boolean read(String content){
        return false;
    }
    
    public String toString(){
        SimpleDateFormat COMPLAINT_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
        return "Complaint{date=" + COMPLAINT_FORMAT.format(date) + ", desc='"+ desc +"'}";
    }
}