package DarvinJmartMH;

/**
 * Write a description of class Jmart here.
 *
 * @author Darvin
 * @version 18/09/2021
 */

public class Jmart
{
    public static void main(String args[]){
        
    }
    
    public static Product create(){
        PriceTag priceTag = new PriceTag(25000);
        Product product = new Product("Fresh Cold Milk", 3, false, priceTag, ProductCategory.KITCHEN);
        return product;
    }
}
