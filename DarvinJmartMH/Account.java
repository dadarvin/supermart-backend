package DarvinJmartMH;


/**
 * Write a description of class Account here.
 *
 * @author Darvin
 * @version Modul 3
 */
public class Account extends Recognizable implements FileParser
{
    public String name;
    public String email;
    public String password;
    
    public Account(int id, String name, String email, String password){
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public boolean read(String content){
        return false;
    }
    
    /*
     * @return Mengembalikan informasi akun
     */
    public String toString(){
        return "name: " + this.name + 
        "\nemail: " + this.email + 
        "\npassword: "+ this.password;
    }
}
