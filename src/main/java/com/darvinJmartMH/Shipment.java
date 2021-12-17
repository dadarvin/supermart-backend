package com.darvinJmartMH;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

/**
 * Write a description of class Shipment here.
 *
 * @author Darvin
 * @version Modul 3 Tutam
 */
public class Shipment
{
    public String address;
    public int cost;
    public byte plan;
    public String receipt;

    public static final SimpleDateFormat ESTIMATION_FORMAT = new SimpleDateFormat("E MMM dd yyyy");
    public static Plan INSTANT = new Plan((byte)(1<<0));
    public static Plan SAME_DAY = new Plan((byte)(1<<1));
    public static Plan NEXT_DAY = new Plan((byte)(1<<2));
    public static Plan REGULER = new Plan((byte)(1<<3));
    public static Plan KARGO = new Plan((byte)(1<<4));

    //innerClass untuk jenis kurir dari ShipmentDuration
    static class Plan{
        public final byte bit;

        /**
         * Constructor untuk menginstansiasi objek Plan
         * @param bit bit merepresentasikan jenis shipment yang dipilih
         */
        private Plan(byte bit){
            this.bit = bit;
        }
    }   
    
    //innerClass dari ShipmentDuration untuk mencari kesamaan
//    class MultiDuration{
//        public byte bit;
//
//         public MultiDuration(Duration... args){
//            byte kurir = args[0].bit;
//
//            for(int i=1; i<args.length; i++){
//                kurir = (byte) (kurir | args[i].bit);
//            }
//
//            bit = kurir;
//        }
//
//        public boolean isDuration(Duration reference){
//            return ( (this.bit & reference.bit) == reference.bit );
//        }
//    }
    /**
     * Constructor untuk menginstansiasi objek Shipment
     * @param address alamat pembeli
     * @param cost harga shipment / pengiriman produk
     * @param plan tipe shipment dalam bentuk byte
     * @param receipt receipt / nomor barcode
     */
    public Shipment(String address, int cost, byte plan, String receipt) {
        this.address = address;
        this.cost = cost;
        this.plan = plan;
        this.receipt = receipt;
    }

    /**
     * Method untuk menentukan waktu suatu produk akan tiba berdasarkan shipment
     * @param reference tanggal pembelian suatu produk
     * @return tanggal produk akan tiba berdasarkan jenis shipment
     */
    public String getEstimatedArrival(Date reference)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(reference);
        if((plan == INSTANT.bit) || (plan == SAME_DAY.bit))
        {
			//Arrive in the same day
            cal.add(Calendar.DATE, 0);          
        }
        else if(plan == NEXT_DAY.bit)
        {
			//Arrive Tomorrow
            cal.add(Calendar.DATE, 1);          
        }
        else if(plan == REGULER.bit)
        {
			//Arrive the day after tomorrow
            cal.add(Calendar.DATE, 2);          
        }
        else if(plan == KARGO.bit)
        {
			//Arrive in a week
            cal.add(Calendar.DATE, 7);          
        }
        String currentDate = ESTIMATION_FORMAT.format(cal.getTime());
        return currentDate;
    }

    /**
     * Method untuk melakukan pengecekan terhadap shipment yang digunakan
     * @param reference tipe shipment yang digunakan
     * @return true jika sudah sesuai, false jika sebaliknya
     */
    public boolean isDuration(Plan reference){
        return (this.plan & reference.bit) == reference.bit;
    }

    /**
     * Method untuk mengecek jenis shipment yang digunakan berdasarkan parameter object btye
     * @param object nilai objek byte
     * @param reference tipe shipment yang digunakan
     * @return true=sesuai; false=tidak sesuai
     */
    public boolean isDuration(byte object, Plan reference){
        return (object & reference.bit) == reference.bit;
    }
}
