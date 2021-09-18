package DarvinJmartMH;

/**
 * Write a description of class Jmart here.
 *
 * @author Darvin
 * @version 18/09/2021
 */

enum ProductCategory{
    BOOK, KITCHEN, ELECTRONIC, FASHION, GAMING, GADGET, MOTHERCARE, FURNITURE, JEWELRY, TOYS, 
    FNB, STATIONERY, SPORTS, AUTOMOTIVE, PETCARE, ART_CRAFT, CARPENTRY, MISCELLANEOUS, 
    PROPERTY, TRAVEL, WEDDING;

    ProductCategory(){
    
    }
}

class PriceTag{
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
            return (price - (price * discount));
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

class ProductRating{
    private long total;
    private long count;
    
    public ProductRating(){
        this.total = 0;
        this.count = 0;
    }
    
    public void insert(int rating){
        total += rating;
        count++;
    }
    
    public double getAverage(){
        return ((double)total/count);
    }
    
    public long getCount(){
        return count;
    }
    
    public long getTotal(){
        return total;
    }
}

class Product{
    private static int idCounter = 0;
    public int id;
    public String name;
    public int weight;
    public boolean conditionUsed;
    
    public PriceTag priceTag;
    public ProductCategory category;
    public ProductRating rating;
    
    public Product(String name, int weight, boolean conditionUsed,
    PriceTag priceTag, ProductCategory category){
        id = idCounter;
        idCounter++;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.priceTag = priceTag;
        this.category = category;
        this.rating = new ProductRating();

    }
}

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
