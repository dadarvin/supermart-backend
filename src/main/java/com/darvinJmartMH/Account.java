package com.darvinJmartMH;
import com.darvinJmartMH.dbjson.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class untuk instansiasi akun pada jmart
 *
 * @author Darvin
 * @version Modul 3
 */
public class Account extends Serializable
{
    
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9&*_~]+(\\.[a-zA-Z0-9&*_~]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?!.* ).{8,}$";
    public double balance;
    public String name;
    public String email;
    public String password;
    public Store store;

    public Account(String name, String email, String password, double balance) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }
   
    public boolean validate(){
        Pattern emailPattern = Pattern.compile(REGEX_EMAIL);
        Pattern passwordPattern = Pattern.compile(REGEX_PASSWORD);

        Matcher emailMatcher = emailPattern.matcher(this.email);
        Matcher passwordMatcher = passwordPattern.matcher(this.password);

        boolean emailMatch = emailMatcher.find();
        boolean passwordMatch = passwordMatcher.find();

        return (emailMatch && passwordMatch);
    }

    public String toString()
    {
        String str = "name: " + this.name + "\nemail: " + this.email + "\npassword: " + this.password;
        return str;
    }
}
