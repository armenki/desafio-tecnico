package com.desafio.tecnico.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.tecnico.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {

	boolean existsByEmail(String email);
}
