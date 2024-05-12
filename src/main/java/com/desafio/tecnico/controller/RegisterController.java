package com.desafio.tecnico.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.tecnico.dto.RequestUserDTO;
import com.desafio.tecnico.dto.ResponseUserDTO;
import com.desafio.tecnico.dto.UpdateUserDTO;
import com.desafio.tecnico.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "desafio/tecnico/users/")
@Validated
public class RegisterController {

	private UserService userService;

	@PostMapping("create")
	public ResponseEntity<ResponseUserDTO> create(@RequestBody @Valid RequestUserDTO requestUserDTO) throws Exception {

		ResponseUserDTO saved = userService.createUser(requestUserDTO);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@GetMapping("List")
	public ResponseEntity<List<ResponseUserDTO>> getAllUsers() throws Exception {
		List<ResponseUserDTO> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<ResponseUserDTO> getUserById(@Valid @PathVariable("id") UUID userId) throws Exception {
		ResponseUserDTO user = userService.getUserById(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUser(@Valid @PathVariable("id") UUID userId) throws Exception {
		userService.deleteUser(userId);
		return new ResponseEntity<>("Usuario eliminado", HttpStatus.OK);
	}

	@PutMapping("update/{id}")
	public ResponseEntity<ResponseUserDTO> updateUser(@Valid @PathVariable("id") UUID userId,
			@Valid @RequestBody UpdateUserDTO updateUserDTO) throws Exception {
		updateUserDTO.setId(userId);
		ResponseUserDTO updatedUser = userService.updateUser(updateUserDTO);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

}
