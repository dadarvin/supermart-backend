package DarvinJmartMH;
import java.util.Date;

/**
 * Write a description of class Invoice here.
 *
 * @author Darvin
 * @version Modul 3 Tutam
 */
public abstract class Invoice extends Recognizable implements FileParser
{
    public Date date;
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;
    public Status status;
    
    //Enum untuk status dan rating pengiriman
    enum Status{
        WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT,
        FINISHED, FAILED;
    }
    
    enum Rating{
        NONE, BAD, NEUTRAL, GOOD;
    }
    
    protected Invoice(int id, int buyerId, int productId){
        super(id);
        this.date = new Date();
        this.buyerId = buyerId;
        this.rating = Rating.NEUTRAL;
        this.status = Status.ON_PROGRESS;
    }
    
    public boolean read(String content){
        return false;
    }
    
    public abstract double getTotalPay();

}
