package DarvinJmartMH;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Write a description of class Account here.
 *
 * @author Darvin
 * @version Modul 3
 */
public class Account extends Recognizable
{
    
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9&*_~]+(\\.[a-zA-Z0-9&*_~]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?!.* ).{8,}$";
    public String name;
    public String email;
    public String password;
    
    public Account(int id, String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    /*
     * @return Mengembalikan informasi akun
     */
    /*
    public String toString(){
        return "name: " + this.name + 
        "\nemail: " + this.email + 
        "\npassword: "+ this.password;
    }
    */
   
    public boolean validate(){
        Pattern emailPattern = Pattern.compile(REGEX_EMAIL);
        Pattern passwordPattern = Pattern.compile(REGEX_PASSWORD);
        Matcher emailMatcher = emailPattern.matcher(this.email);
        Matcher passwordMatcher = passwordPattern.matcher(this.password);
        boolean emailMatch = emailMatcher.find();
        boolean passwordMatch = passwordMatcher.find();
        
        if(emailMatch == true && passwordMatch == true){
            return true;
        } else {
            return false;
        }
    }
}
