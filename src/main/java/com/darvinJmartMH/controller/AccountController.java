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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class yang berisi perintah Spring untuk menerima request modifikasi terhadap objek Account
 */
@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
	public static final String REGEX_EMAIL = "^[a-zA-Z0-9&*_~]+(\\.[a-zA-Z0-9&*_~]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
	public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?!.* ).{8,}$";
	public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
	public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);

	/**
	 * Method untuk melakukan enkripsi pada variabel password
	 * @param password string password yang akan dienkripsi
	 * @return hasil enkripsi yang telah dilakukan pada password
	 * @throws NoSuchAlgorithmException atau algoritma tidak exist
	 */
	public String hashPassword(String password){
		try{
			String generatedPassword = null;

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] bytes = md.digest();

			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < bytes.length; i++){
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
			return generatedPassword;
		} catch (NoSuchAlgorithmException e){
			e.printStackTrace();
			return password;
		}
	}

	@JsonAutowired(value = Account.class, filepath = "account.json")
	public static JsonTable<Account> accountTable;
	
	/**
	 * Method untuk menemukan list yang berisikan objek Account yang terdapat pada tabel json
	 * @return list yang berisikan objek Account yang terdaftar pada tabel json
	 */
	public JsonTable<Account> getJsonTable(){
		return accountTable;
	}

	@GetMapping
	String index() {
		return "account page";
	}
	/**
	 * Method untuk memvalidasi kredensial login yang dimasukkan oleh user
	 * @param email email dari objek Account
	 * @param password password dari objek Account
	 * @return objek Account yang memiliki email dan password yang cocok, dan null jika tidak ada objek Account yang sesuai
	 */
	@PostMapping("/login")
	Account login(
			@RequestParam String email,
			@RequestParam String password
	)
	{
		for(Account account : accountTable) {
			if(account.email.equals(email) && account.password.equals(hashPassword(password)))
				return account;
		}
		return null;
	}

	/**
	 * Method untuk registrasi Account baru dengan menginstansiasi objek Account
	 * @param name nama yang akan didaftarkan
	 * @param email email yang akan didaftarkan
	 * @param password password yang akan didaftarkan
	 * @return objek Account yang sudah berhasil didaftarkan atau null jika Account tidak berhasil didaftarkan
	 */
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

			Account regAccount = new Account(name, email, hashPassword(password), 0);
			accountTable.add(regAccount);
			return regAccount;

		} else {
			return null;
		}
	}
	
	/**
	 * Method untuk melakukan pendaftaran toko pada suatu existing acount
	 * @param id id dari suatu objek Account yang ingin mendaftarkan toko
	 * @param name nama dari toko yang akan didaftarkan
	 * @param address alamat dari toko yang akan didaftarkan
	 * @param phoneNumber nomor telepon dari toko yang akan didaftarkan
	 * @return objek Store yang berhasil didaftarkan atau null jika tidak berhasil didaftarkan
	 */
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

	/**
	 * Method untuk melakukan top up atau penambahan balance pada suatu account
	 * @param id id dari objek Account yang ingin melakukan top up
	 * @param balance balance dari objek Account yang ingin melakukan top up sebelum di-top up
	 * @return true jika balance pada objek Account berhasil di top up, false jika tidak berhasil
	 */
	@PostMapping("/{id}/topUp")
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
/**
	 * Method untuk mengambil objek Account dari json berdasarkan id nya
	 * @param id nilai id dari suatu objek Account
	 * @return objek Account yang memiliki id sesuai dengan parameter
	 */
	@GetMapping("/{id}")
	public Account getByAccountId(@PathVariable int id) { return getById(id); }

}