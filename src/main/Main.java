package main;

import java.util.Scanner;

public class Main {
	static Authenticator auth = new Authenticator();
	static Scanner stdin = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		if (auth.check("big", stdin.nextLine())) 
			System.out.println("YAY");
		else 
			System.out.println("NOPE");
		
	}
}
