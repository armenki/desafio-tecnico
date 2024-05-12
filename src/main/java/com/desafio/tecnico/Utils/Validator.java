package com.desafio.tecnico.Utils;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Validator {
	public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
	public static final String PASSWORD_REGEX = "^[a-zA-Z0-9]+$";

	public static boolean isValidEmail(String email) {
		if (!isNotBlank(email)) {
			return false;
		}		
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public static boolean isNotBlank(String input) {
		return input != null && !input.trim().isEmpty();
	}

	public static boolean isValidPassword(String password) {
		if (password == null || password.isEmpty()) {
			return false;
		}

		Pattern pattern = Pattern.compile(PASSWORD_REGEX);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}
	
    public static boolean isValidInteger(String input) {
        if (!isNotBlank(input)) {
            return false;
        }
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
