package br.com.api.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CryptUtils {

	public static String generateBCrypt(String password) {
		if (!password.isEmpty()) {
			BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
			return bCryptEncoder.encode(password.trim());
		} else {
			return password;
		}
	}

	public static boolean isPassWordValid(String password, String encodedPassword) {
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		return bCryptEncoder.matches(password, encodedPassword);
	}
}
