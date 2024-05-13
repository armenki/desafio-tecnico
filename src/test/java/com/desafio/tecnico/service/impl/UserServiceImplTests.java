package com.desafio.tecnico.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.desafio.tecnico.dto.PhoneDTO;
import com.desafio.tecnico.dto.RequestUserDTO;
import com.desafio.tecnico.dto.ResponseUserDTO;
import com.desafio.tecnico.entity.User;
import com.desafio.tecnico.jwt.JwtUtils;
import com.desafio.tecnico.repository.UserRepository;

class UserServiceImplTests {

	@Test
	public void testCreateUser() {
		JwtUtils jwtUtilsMock = mock(JwtUtils.class);
		UserRepository userRepositoryMock = mock(UserRepository.class);
		ModelMapper modelMapperMock = mock(ModelMapper.class);
		UserServiceImpl userService = new UserServiceImpl(jwtUtilsMock, userRepositoryMock, modelMapperMock);
		RequestUserDTO requestUserDTO = new RequestUserDTO();
		requestUserDTO.setName("asd");
		requestUserDTO.setEmail("aeeekq@dominio.cl");
		requestUserDTO.setPassword("Hunter2222");
		List<PhoneDTO> phones = new ArrayList<>();
		PhoneDTO phone1 = new PhoneDTO();
		phone1.setNumber("123456789");
		phone1.setCityCode("123");
		phone1.setContrycode("5478");
		phones.add(phone1);
		requestUserDTO.setPhones(phones);
		ResponseUserDTO responseUserDTO = new ResponseUserDTO();
		User user = new User();
		user.setName(requestUserDTO.getName());
		user.setEmail(requestUserDTO.getEmail());
		when(userRepositoryMock.existsByEmail("test@test.cl")).thenReturn(false);
		when(modelMapperMock.map(requestUserDTO, User.class)).thenReturn(user);
		when(jwtUtilsMock.generateAccessToken(user)).thenReturn("test");
		when(userRepositoryMock.save(user)).thenReturn(user);
		when(modelMapperMock.map(user, ResponseUserDTO.class)).thenReturn(responseUserDTO);
		ResponseUserDTO response = userService.createUser(requestUserDTO);
		assertNotNull(response);
	}

	@Test
	public void testGetAllUsers() {
		UserRepository userRepositoryMock = mock(UserRepository.class);
		ModelMapper modelMapperMock = mock(ModelMapper.class);
		UserServiceImpl userService = new UserServiceImpl(null, userRepositoryMock, modelMapperMock);
		Date currentDate = new Date(0);
		User user1 = new User(UUID.randomUUID(), "John Doe", "john@example.com", "password", new HashSet<>(),
				currentDate, currentDate, currentDate, "mockedtoken", true);
		User user2 = new User(UUID.randomUUID(), "Jane Smith", "jane@example.com", "password", new HashSet<>(),
				currentDate, currentDate, currentDate, "mockedtoken", true);
		List<User> users = Arrays.asList(user1, user2);
		when(userRepositoryMock.findAll()).thenReturn(users);
		List<ResponseUserDTO> response = userService.getAllUsers();
		assertNotNull(response);
		assertEquals(users.size(), response.size());
	}

	@Test
	public void testGetUserById() {
		UserRepository userRepositoryMock = mock(UserRepository.class);
		ModelMapper modelMapperMock = mock(ModelMapper.class);
		UserServiceImpl userService = new UserServiceImpl(null, userRepositoryMock, modelMapperMock);
		UUID userId = UUID.randomUUID();
		User user = new User(userId, "John Doe", "john@example.com", "password", new HashSet<>(), new Date(0),
				new Date(0), new Date(0), "mockedtoken", true);
		when(userRepositoryMock.findById(userId)).thenReturn(Optional.of(user));
		when(modelMapperMock.map(any(User.class), eq(ResponseUserDTO.class))).thenReturn(new ResponseUserDTO(userId,
				user.getName(), user.getEmail(), user.getToken(), null, null, null, null, null, null));
		ResponseUserDTO response = userService.getUserById(userId);
		assertNotNull(response);
		assertEquals(user.getName(), response.getName());
		assertEquals(user.getEmail(), response.getEmail());
	}

	@Test
	public void testDeleteUser_ExistingUser() {
		UserRepository userRepositoryMock = mock(UserRepository.class);
		UserServiceImpl userService = new UserServiceImpl(null, userRepositoryMock, null);
		UUID userId = UUID.randomUUID();
		when(userRepositoryMock.existsById(userId)).thenReturn(true);
		userService.deleteUser(userId);
	}

}
