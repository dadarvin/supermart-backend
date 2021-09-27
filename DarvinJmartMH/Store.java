package DarvinJmartMH;


/**
 * Write a description of class Store here.
 *
 * @author Darvin
 * @version Modul 3
 */
public class Store extends Recognizable implements FileParser
{
    public String name;
    public String address;
    public String phoneNumber;
    
    public Store(int accountId, String name, String address, String phoneNumber){
        super(accountId);
        this.name = name;
        this.address= address;
        this.phoneNumber = phoneNumber;
    }
    
    public Store(Account account, String name, String address, String phoneNumber){
        super(account.id);
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    
    public boolean read(String content){
        return false;
    }
    
    /*
     * @return Mengembalikan informasi toko
     */
    public String toString(){
        return "name: " + this.name + 
        "\naddress: " + this.address + 
        "\nphoneNumber: " + this.phoneNumber;
    }
}
