package com.desafio.tecnico.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDTO {

	@NotBlank
	private String number;

	@NotBlank
	@JsonProperty(value = "citycode")
	private String cityCode;

	@NotBlank
	@JsonProperty(value = "contrycode")
	private String contrycode;

}
