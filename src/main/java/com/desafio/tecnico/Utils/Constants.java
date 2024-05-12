package com.desafio.tecnico.Utils;

public class Constants {
	private Constants() {
	}

	public static final String INVALID_EMAIL_FORMAT_MESSAGE = "El formato del correo electrónico no es válido";
	public static final String INVALID_PASSWORD_FORMAT_MESSAGE = "El formato del password no es válido";
	public static final String INVALID_PHONE_NUMBER_MESSAGE = "El campo '%s' no contiene un número válido";
	public static final String EMAIL_ALREADY_REGISTERED_MESSAGE = "El correo ya está registrado";
	public static final String USER_NOT_FOUND_MESSAGE = "El usuario no existe";
	public static final String PHONE_NUMBER_FIELD_NUMBER = "number";
	public static final String PHONE_NUMBER_FIELD_CITY_CODE = "cityCode";
	public static final String PHONE_NUMBER_FIELD_COUNTRY_CODE = "countryCode";

	public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
	public static final String PASSWORD_REGEX = "^[a-zA-Z0-9]{5,15}$";

}
