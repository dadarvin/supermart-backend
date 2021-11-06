package DarvinJmartMH;
import java.util.Date;
import java.util.ArrayList;

/**
 * Write a description of class Invoice here.
 *
 * @author Darvin
 * @version Modul 3 Tutam
 */
public abstract class Invoice extends Serializable
{
    public Date date;
    public ArrayList<Record> history = new ArrayList<Record>();
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
    
    class Record{
        public Date date;
        public String message;
        public Status status;
    }
    
    protected Invoice(int buyerId, int productId){
        this.date = new Date();
        this.buyerId = buyerId;
        this.productId = productId;
        this.rating = Rating.NEUTRAL;
        this.status = Status.ON_PROGRESS;
    }

    public abstract double getTotalPay();

}
