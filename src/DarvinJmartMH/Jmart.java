package DarvinJmartMH;
import java.util.Date;

/**
 * Write a description of class Jmart here.
 *
 * @author Darvin
 * @version 18/09/2021
 */

public class Jmart
{
    public static void main(String args[]){
        System.out.println("Darvin - 1906300694");
        System.out.println(Shipment.Duration.KARGO.getEstimatedArrival(new Date()));
        Complaint complaint = new Complaint(001, "Pengiriman sangat cepat, tapi kurir tersesat");
        System.out.print(complaint);
        Account account = new Account(1, "Darvin", "darvin.yap@ui.ac.id", "Darvin1a1a");
        System.out.println(account.validate());
    }
    
    /*
    public static Product createProduct(){
        PriceTag priceTag = new PriceTag(25000);
        Product product = new Product("Fresh Cold Milk", 3, false, priceTag, ProductCategory.KITCHEN);
        return product;
    }
    
    public static Coupon createCoupun(){
        return new Coupon("MakanEnak",30,Coupon.Type.DISCOUNT,20.0,5.0);
    }
    
   
    public static ShipmentDuration createShipmentDuration() {
        return new ShipmentDuration(ShipmentDuration.INSTANT, ShipmentDuration.KARGO);
    }
    */
}
