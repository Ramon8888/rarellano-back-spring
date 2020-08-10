package com.backend.rarellano.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.rarellano.entities.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Serializable>{
	
	boolean existsByUser(String user);
	// public abstract User findByUser(String user);
	public abstract List<User> findAll();
	public abstract User findByProject(Integer project);
	public Optional <User> findByUser(String use);
	public Optional<User> findById(Long id);

}
