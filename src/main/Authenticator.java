package main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import main.db.DefaultSaver;
import main.db.IDataSaver;

public class Authenticator {
	private String username;
	private String password_hash;
	private MessageDigest md5;
	@SuppressWarnings("rawtypes")
	private IDataSaver data;
	
	public Authenticator() {
		this("default", "default", new DefaultSaver<String, String>());
	}
	
	public Authenticator(String username, String password) {
		this(username, password, null);
	}
	
	@SuppressWarnings("rawtypes")
	public Authenticator(String username, String password, IDataSaver db) {
		try {
			md5 = MessageDigest.getInstance("MD5");	
		} catch(NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		}
		if (db != null) {
			data = db;			
		} else {
			data = null;
		}
		
		this.username = username;
		this.password_hash = new String(md5.digest(password.getBytes()));
	}

	@SuppressWarnings("unchecked")
	public void addUser(String username, String password) {
		String hash = new String(md5.digest(password.getBytes()));
		data.save(username, hash);
	}
	
	@SuppressWarnings("unchecked")
	public boolean check(String username, String password) 
	{
		// Se non c'è un metodo di salvataggio dei dati
		if (data == null) return password_hash.equals(new String(md5.digest(password.getBytes()))) && this.username.equals(username);
		
		String pass = (String) data.fetch(username);
		
		if (pass != null) {
			return pass.equals(password);	
		}
		return false;
	}
	
}
