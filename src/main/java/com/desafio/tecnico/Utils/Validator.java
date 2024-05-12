package com.desafio.tecnico.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	public static boolean isValidEmail(String email) {
		if (!isNotBlank(email)) {
			return false;
		}
		Pattern pattern = Pattern.compile(Constants.EMAIL_REGEX);
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

		Pattern pattern = Pattern.compile(Constants.PASSWORD_REGEX);
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
