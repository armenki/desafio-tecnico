package com.desafio.tecnico.service.impl;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.tecnico.Utils.Constants;
import com.desafio.tecnico.Utils.Validator;
import com.desafio.tecnico.dto.PhoneDTO;
import com.desafio.tecnico.dto.RequestUserDTO;
import com.desafio.tecnico.dto.ResponseUserDTO;
import com.desafio.tecnico.dto.UpdateUserDTO;
import com.desafio.tecnico.entity.Phone;
import com.desafio.tecnico.entity.User;
import com.desafio.tecnico.exception.InvalidFormatException;
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
		validateUser(requestUserDTO);
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
		User userFound = userRepository.findById(userId)
				.orElseThrow(() -> new InvalidFormatException(Constants.USER_NOT_FOUND_MESSAGE));
		return modelMapper.map(userFound, ResponseUserDTO.class);
	}

	@Override
	public void deleteUser(UUID userId) {
		if (!userRepository.existsById(userId)) {
	        throw new InvalidFormatException(Constants.USER_NOT_FOUND_MESSAGE);
	    }
	    userRepository.deleteById(userId);
	}

	public ResponseUserDTO updateUser(UpdateUserDTO updateUserDTO) throws Exception {
		User existingUser = userRepository.findById(updateUserDTO.getId())
				.orElseThrow(() -> new InvalidFormatException(Constants.USER_NOT_FOUND_MESSAGE));
		validateUser(updateUserDTO);
		existingUser.setName(updateUserDTO.getName());
		existingUser.setEmail(updateUserDTO.getEmail());
		existingUser.setPassword(updateUserDTO.getPassword());

		Set<Phone> phones = updateUserDTO.getPhones().stream().map(phoneDTO -> {
			Phone phone = modelMapper.map(phoneDTO, Phone.class);
			phone.setUserId(existingUser.getId());
			return phone;
		}).collect(Collectors.toSet());

		existingUser.getPhones().clear();
		existingUser.getPhones().addAll(phones);

		User updatedUser = userRepository.save(existingUser);
		return modelMapper.map(updatedUser, ResponseUserDTO.class);
	}

	private void validateUser(RequestUserDTO requestUserDTO) {
		if (!Validator.isValidEmail(requestUserDTO.getEmail())) {
			throw new InvalidFormatException(Constants.INVALID_EMAIL_FORMAT_MESSAGE);
		}
		if (!Validator.isValidPassword(requestUserDTO.getPassword())) {
			throw new InvalidFormatException(Constants.INVALID_PASSWORD_FORMAT_MESSAGE);
		}
		String invalidPhoneFieldName = InvalidNumber(requestUserDTO.getPhones());
		if (invalidPhoneFieldName != null) {
			throw new InvalidFormatException(
					String.format(Constants.INVALID_PHONE_NUMBER_MESSAGE, invalidPhoneFieldName));
		}
		if (userRepository.existsByEmail(requestUserDTO.getEmail())) {
			throw new InvalidFormatException(Constants.EMAIL_ALREADY_REGISTERED_MESSAGE);
		}
	}

	private void validateUser(UpdateUserDTO updateUserDTO) {
		if (!Validator.isValidEmail(updateUserDTO.getEmail())) {
			throw new InvalidFormatException(Constants.INVALID_EMAIL_FORMAT_MESSAGE);
		}
		if (!Validator.isValidPassword(updateUserDTO.getPassword())) {
			throw new InvalidFormatException(Constants.INVALID_PASSWORD_FORMAT_MESSAGE);
		}
		String invalidPhoneFieldName = InvalidNumber(updateUserDTO.getPhones());
		if (invalidPhoneFieldName != null) {
			throw new InvalidFormatException(
					String.format(Constants.INVALID_PHONE_NUMBER_MESSAGE, invalidPhoneFieldName));
		}
	}

	private String InvalidNumber(List<PhoneDTO> phones) {
		for (PhoneDTO phone : phones) {
			if (!Validator.isValidInteger(phone.getNumber())) {
				return Constants.PHONE_NUMBER_FIELD_NUMBER;
			}
			if (!Validator.isValidInteger(phone.getCityCode())) {
				return Constants.PHONE_NUMBER_FIELD_CITY_CODE;
			}
			if (!Validator.isValidInteger(phone.getContrycode())) {
				return Constants.PHONE_NUMBER_FIELD_COUNTRY_CODE;
			}
		}
		return null;
	}

}
