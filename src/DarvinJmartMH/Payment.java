package DarvinJmartMH;


/**
 * Write a description of class Payment here.
 *
 * @author Darvin
 * @version Modul 3
 */
public class Payment extends Invoice implements Transactor
{
    public int productCount;
    public Shipment shipment;
    
    /*
     * @param Berisi id, banyak produk, dan jenis pengiriman yang dilakukan
     */
    public Payment(int id, int buyerId, int productId, int productCount, Shipment shipment){
        super(id, buyerId, productId);
        this.productCount = productCount;
        this.shipment = shipment;
    }
    
     public boolean validate()
    {
        return false;
    }
    
    public Invoice perform()
    {
        return null;
    }
    
    public double getTotalPay(){
        return 0;
    }
}
