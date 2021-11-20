// TODO sesuaikan dengan package Anda: package com.alvinJmartRK.controller;


// TODO sesuaikan dengan package Anda: import com.alvinJmartRK.Account;
package com.darvinJmartMH.controller;
import com.darvinJmartMH.Account;
import com.darvinJmartMH.Algorithm;
import com.darvinJmartMH.Store;
import com.darvinJmartMH.dbjson.JsonAutowired;
import com.darvinJmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
	public static final String REGEX_EMAIL = "^[a-zA-Z0-9&*_~]+(\\.[a-zA-Z0-9&*_~]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
	public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?!.* ).{8,}$";
	public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
	public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);

	@JsonAutowired(value = Account.class, filepath = "C:\\@Pen&Pin\\Dar\\OOP\\OOP_prak\\jmart\\account.json")
	public static JsonTable<Account> accountTable;

	public JsonTable<Account> getJsonTable(){
		return accountTable;
	}

	@GetMapping
	String index() {
		return "account page";
	}

	@PostMapping("/login")
	Account login(
			@RequestParam String email,
			@RequestParam String password
	)
	{
		for(Account account : accountTable) {
			if(account.email.equals(email) && account.password.equals(password))
				return account;
		}
		return null;
	}


	@PostMapping("/register")
	Account register
	(
		@RequestParam String name,
		@RequestParam String email,
		@RequestParam String password
	)
	{
		Matcher emailMatcher = REGEX_PATTERN_EMAIL.matcher(email);
		boolean emailMatch = emailMatcher.find();
		Matcher passwordMatcher = REGEX_PATTERN_PASSWORD.matcher(password);
		boolean passwordMatch = passwordMatcher.find();
		boolean unique = true;

		for(Account acc: accountTable){
			if(acc.email.equals(email)){
				unique = false;
				break;
			}
		}

		if(!name.isBlank() && emailMatch && passwordMatch && unique){

			Account regAccount = new Account(name, email, password, 0);
			accountTable.add(regAccount);
			return regAccount;

		} else {
			return null;
		}
	}

	@PostMapping("/{id}/registerStore")
	Store registerStore
	(
			@PathVariable int id,
			@RequestParam String name,
			@RequestParam String address,
			@RequestParam String phoneNumber
	)
	{
		Account acc = Algorithm.<Account> find(accountTable, obj -> obj.id == id);
		if(acc == null || acc.store != null)
		{
			return null;
		}
		acc.store = new Store(name,address, phoneNumber, 0.0);
		return acc.store;
//		return null;
	}

	@PostMapping("{id}/topUp")
	boolean topUp(
			@PathVariable int id,
			@RequestParam double balance
	){
		Account acc = getById(id);
		if(acc != null){
			acc.balance = acc.balance + balance;
			return true;
		} else {
			return false;
		}
	}

}