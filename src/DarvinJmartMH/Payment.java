package DarvinJmartMH;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Write a description of class Payment here.
 *
 * @author Darvin
 * @version Modul 3
 */
public class Payment extends Invoice
{
    public ArrayList<Record> history = new ArrayList<>();
    public int productCount;
    public Shipment shipment;
    
    /*
     * @param Berisi id, banyak produk, dan jenis pengiriman yang dilakukan
     */
    public Payment(int buyerId, int productId, int productCount, Shipment shipment){
        super(buyerId, productId);
        this.productCount = productCount;
        this.shipment = shipment;
    }

    static class Record{
        public final Date date;
        public Status status;
        public String message;

        public Record(Status status, String message){
            this.status = status;
            this.message = message;
            this.date = Calendar.getInstance().getTime();
        }
    }
    
    public double getTotalPay(Product product){
        return product.price * productCount;
    }
}
