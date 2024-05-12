package com.desafio.tecnico.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.tecnico.Utils.InvalidFormatException;
import com.desafio.tecnico.Utils.Validator;
import com.desafio.tecnico.dto.PhoneDTO;
import com.desafio.tecnico.dto.RequestUserDTO;
import com.desafio.tecnico.dto.ResponseUserDTO;
import com.desafio.tecnico.entity.User;
import com.desafio.tecnico.jwt.JwtUtils;
import com.desafio.tecnico.repository.UserRepository;
import com.desafio.tecnico.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	@Autowired
	private JwtUtils jwt;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseUserDTO createUser(RequestUserDTO requestUserDTO) {
		if (!Validator.isValidEmail(requestUserDTO.getEmail())) {
			throw new InvalidFormatException("El formato del correo electrónico no es válido");
		}
		if (!Validator.isValidPassword(requestUserDTO.getPassword())) {
			throw new InvalidFormatException("El formato del password no es válido");
		}
		String invalidPhoneFieldName = fieldNameOfInvalidPhoneNumber(requestUserDTO.getPhones());
		if (invalidPhoneFieldName != null) {
			throw new InvalidFormatException("El campo '" + invalidPhoneFieldName + "' no contiene un número válido");
		}

		if (userRepository.existsByEmail(requestUserDTO.getEmail())) {
			throw new InvalidFormatException("El correo ya está registrado");
		}
		User user = modelMapper.map(requestUserDTO, User.class);
		user.setToken(jwt.generateAccessToken(user));
		User savedUser = userRepository.save(user);
		return modelMapper.map(savedUser, ResponseUserDTO.class);
	}

	@Override
	public List<ResponseUserDTO> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map((user) -> modelMapper.map(user, ResponseUserDTO.class)).collect(Collectors.toList());
	}

	@Override
	public ResponseUserDTO getUserById(UUID userId) {
		Optional<User> UserFind = userRepository.findById(userId);		
		if (UserFind.isEmpty()) {
			throw new InvalidFormatException("El usuario no existe");
		}
		User userFound = UserFind.get();
		return modelMapper.map(userFound, ResponseUserDTO.class);
	}

	private String fieldNameOfInvalidPhoneNumber(List<PhoneDTO> phones) {
		for (PhoneDTO phone : phones) {
			if (!Validator.isValidInteger(phone.getNumber())) {
				return "number";
			}
			if (!Validator.isValidInteger(phone.getCityCode())) {
				return "cityCode";
			}
			if (!Validator.isValidInteger(phone.getContrycode())) {
				return "contryCode";
			}
		}
		return null;
	}

}
