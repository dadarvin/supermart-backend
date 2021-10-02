package DarvinJmartMH;
import java.util.ArrayList;

/**
 * Write a description of class Filter here.
 *
 * @author Darvin
 * @version Modul 4
 */
public class Filter
{
    private Filter(){
    
    }
    
    public static ArrayList<PriceTag> filterPriceTag(PriceTag[] list, int value, boolean less){
        ArrayList<PriceTag> priceTags = new ArrayList<>();
        
        for(PriceTag tag : list){
            if(less){
                if(tag.getAdjustedPrice() < value){
                    priceTags.add(tag);
                }
            }
            if(!less){
                if(tag.getAdjustedPrice() >= value){
                    priceTags.add(tag);
                }
            }
        }
        return priceTags;
    }
    
    public static void filterProductRating(ArrayList<ProductRating> list, double value, boolean less){
        for(int i = 0; i<list.size(); ++i){
            if(less){
                if(list.get(i).getAverage() < value)
                    list.remove(i);
            }
            if(!less){
                if(list.get(i).getAverage() >= value)
                    list.remove(i);
            }
        }
    }
}
