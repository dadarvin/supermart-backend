package DarvinJmartMH;


/**
 * Write a description of class Shipment here.
 *
 * @author Darvin
 * @version Modul 3 Tutam
 */
public class Shipment implements FileParser
{
    public String address;
    public int shipmentCost;
    public Duration duration;
    public String receipt;
    
    //innerClass untuk jenis kurir dari ShipmentDuration
    static class Duration{
        public static Duration INSTANT = new Duration((byte)(1<<0));
        public static Duration SAME_DAY = new Duration((byte)(1<<1));
        public static Duration NEXT_DAY = new Duration((byte)(1<<2));
        public static Duration REGULER = new Duration((byte)(1<<3));
        public static Duration KARGO = new Duration((byte)(1<<4));
        
        private final byte bit;
        
        private Duration(byte bit){
            this.bit = bit;
        }
        
    }
    
    //innerClass dari ShipmentDuration untuk mencari kesamaan
    class MultiDuration{
        public byte bit;
        
         public MultiDuration(Duration... args){
            byte kurir = args[0].bit;
            
            for(int i=1; i<args.length; i++){
                kurir = (byte) (kurir | args[i].bit);
            }
            
            bit = kurir;    
        }
        
        public boolean isDuration(Duration reference){
            return ( (this.bit & reference.bit) == reference.bit ); 
        }
    }
    
    public Shipment(String address, int shipmentCost, Duration duration, String receipt) {
        this.address = address;
        this.shipmentCost = shipmentCost;
        this.duration = duration;
        this.receipt = receipt;
    } 
    
    public boolean read(String content){
        return false;
    }
    
    public Object write(){
        return FileParser.super.write();    
    }
}
