package com.desafio.tecnico.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUserDTO {
	
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
