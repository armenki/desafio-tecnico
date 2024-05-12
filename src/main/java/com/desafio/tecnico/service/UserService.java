package com.desafio.tecnico.service;

import java.util.List;
import java.util.UUID;

import com.desafio.tecnico.dto.RequestUserDTO;
import com.desafio.tecnico.dto.ResponseUserDTO;

public interface UserService {
	ResponseUserDTO createUser(RequestUserDTO requestUserDTO) throws Exception;

	List<ResponseUserDTO> getAllUsers() throws Exception;

	ResponseUserDTO getUserById(UUID userId) throws Exception;

}
