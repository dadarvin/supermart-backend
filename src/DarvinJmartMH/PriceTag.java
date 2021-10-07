package DarvinJmartMH;


/**
 * Write a description of class PriceTag here.
 *
 * @author Darvin
 * @version 18/09/20
 */
public class PriceTag
{
    
    public final static double COMMISSION_MULTIPLIER = 0.05;
    public final static double BOTTOM_PRICE = 20000.0;
    public final static double BOTTOM_FEE = 1000.0;
    public double discount;
    public double price;
    
    //Constructor dengan parameter price
    public PriceTag(double price){
        this.price = price;
        this.discount = 0.0;
    }
    
    //Constructor dengan parameter price dan discount
    public PriceTag(double price, double discount){
        this.price = price;
        this.discount = discount;
    }    
    
    public double getAdjustedPrice(){
        double afterDisc = getDiscountedPrice() + getAdminFee();
        return afterDisc;
    }
    
    private double getDiscountedPrice(){
        if(discount >= 100.0){
            return 0.0;
        } else{
            return (price - (price * discount/100));
        }
    }
    
    public double getAdminFee(){
        double afterDisc = getDiscountedPrice();
        if( afterDisc <= BOTTOM_PRICE){
            return BOTTOM_FEE;
        } else {
            afterDisc = afterDisc - (afterDisc * COMMISSION_MULTIPLIER);
            return afterDisc;
        }
    }
    
}
