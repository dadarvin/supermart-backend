package DarvinJmartMH;


/**
 * Write a description of class Coupon here.
 *
 * @author Darvin
 * @version 20/09/2021
 */


public class Coupon
{
    
    public enum Type
    {
        DISCOUNT, REBATE;
    }

    public final String name;
    public final int code;
    public final double cut;
    public final Type type;
    public final double minimum;
    
    private boolean used;
    
    public Coupon(String name, int code, Type type, double cut, double minimum){
        used = false;
        
        this.name = name;
        this.code = code;
        this.type = type;
        this.cut = cut;
        this.minimum = minimum;
    }
    
    public boolean isUsed(){
        return used;
    }
    
    public boolean canApply(PriceTag priceTag){
        if(priceTag.getAdjustedPrice() >= minimum && used == false){
            return true;
        } else {
            return false;
        }
    }
    
    public double apply(PriceTag priceTag){
        used = true;
        double price = priceTag.getAdjustedPrice();
        
        if(type == type.DISCOUNT){
            price = priceTag.getAdjustedPrice();
            return ( price - (price* (cut/100) ) );
        } else if (type == type.REBATE) {
            return ( price - priceTag.price);
        } else {
            return price;
        }
    }
}
