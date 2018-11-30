package com.josephmfaulkner;

public class Configuration {

	public static final int LISTENING_PORT = (int) Integer.parseInt(System.getenv().get("PORT")); // 7500; 
	public static final int ERRORS_ALLOWED = 10;
	public static final String PUBLIC_FOLDER = "public_html";
	
}
