package DarvinJmartMH;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Write a description of class Store here.
 *
 * @author Darvin
 * @version Modul 3
 */
public class Store extends Recognizable
{
    public static final String REGEX_NAME = "^[A-Z](?!.*(\\s)\1).{4,20}$";
    public static final String REGEX_PHONE = "^\\d{9,12}$"; 
    public String name;
    public String address;
    public String phoneNumber;
    
    public Store(int accountId, String name, String address, String phoneNumber){
        this.name = name;
        this.address= address;
        this.phoneNumber = phoneNumber;
    }
    
    public Store(Account account, String name, String address, String phoneNumber){
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
    
    public boolean validate(){
        Pattern namePattern = Pattern.compile(REGEX_NAME);
        Pattern phonePattern = Pattern.compile(REGEX_PHONE);
        Matcher nameMatcher = namePattern.matcher(this.name);
        Matcher phoneMatcher = phonePattern.matcher(this.phoneNumber);
        boolean nameMatch = nameMatcher.find();
        boolean phoneMatch = phoneMatcher.find();
        
        if(nameMatch == true && phoneMatch == true){
            return true;
        } else {
            return false;
        }
    }
}
