package DarvinJmartMH;


/**
 * Write a description of class Product here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
class Product extends Recognizable implements FileParser{
    private static int idCounter = 0;
    public String name;
    public int weight;
    public boolean conditionUsed;
    public int storeId;
    
    public PriceTag priceTag;
    public ProductCategory category;
    public ProductRating rating;
    
    public Store store;
    
    public Product(int id, int storeId, String name, int weight, boolean conditionUsed, 
    PriceTag priceTag, ProductCategory category){
        super(id);
        this.storeId = storeId;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.priceTag = priceTag;
        this.category = category;
        this.rating = new ProductRating();
    }
    
    public Product(int id, Store store, String name, int weight, boolean conditionUsed, 
    PriceTag priceTag, ProductCategory category){
        super(id);
        this.store = store;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.priceTag = priceTag;
        this.category = category;
    }
    
    public boolean read(String content){
        return false;
    }
    
    public Object write(){
        return null;
    }
    
    public static Object newInstance(String content){
        return null;
    }
}
