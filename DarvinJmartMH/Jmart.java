package DarvinJmartMH;

/**
 * Write a description of class Jmart here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Jmart
{
    public static void main(String args[]){
        System.out.println("Warm-Up Program - Darvin (1906300694)");
        
        System.out.println("Method getPromo : " + getPromo());
        System.out.println("Method getCustomer : " + getCustomer());
        System.out.println("Method getDiscountPercentage : " + getDiscountPercentage(1000, 0));
        System.out.println("Method getDiscountedPrice : " + getDiscountedPrice(1000,10.0f));
        System.out.println("Method getOriginalPrice : " + getOriginalPrice(1000, 0.0f));
        System.out.println("Method getComissionMultiplier : " + getComissionMultiplier());
        System.out.println("Method getAdjustedPrice : " + getAdjustedPrice(500));
        System.out.println("Method getAdminFee : " + getAdminFee(1000));
    }
    
    public static int getPromo(){
        return 0;
    }
    
    public static String getCustomer(){
        return "oop";
    }
    
    public static float getDiscountPercentage(int before, int after){
        if(before < after){
            return 0.0f;
        } 
        
        float diff = before - after;
        float discPercentage = diff / before * 100;
        return (discPercentage);
        
    }
    
    public static int getDiscountedPrice(int price, float discountPercentage){
        float totDiscount;
        if(discountPercentage > 100.0f){
            totDiscount = 100.0f;
        } else {
            totDiscount = discountPercentage;
        }
        
        float discountedPrice = price - (totDiscount * price/100);
        int discountedPriceint = (int) discountedPrice;
        return discountedPriceint;
    }
    
    public static int getOriginalPrice(int discountedPrice, float discountPercentage){
        float discPriceflt = discountedPrice;
        float originPrice = (100/(100-(discountPercentage))) * discPriceflt;
        int originPriceint = (int) originPrice;
        return originPriceint;
    }
    
    public static float getComissionMultiplier(){
        return 0.05f;
    }
    
    public static int getAdjustedPrice(int price){
        
        float priceflt = price + (price * getComissionMultiplier());
        int priceint = (int) priceflt;
        return priceint;
    }
    
    public static int getAdminFee(int price){
        float comission = price * getComissionMultiplier();
        int resComission = (int) comission;
        return resComission;
    }
}
