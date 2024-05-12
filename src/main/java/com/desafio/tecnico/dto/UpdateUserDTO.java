package com.desafio.tecnico.dto;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO {
	private UUID id;

	@NotBlank
	private String name;

	@NotBlank
	private String email;

	@NotBlank
	private String password;

	@Valid
	@NotNull
	private List<PhoneDTO> phones;

}
