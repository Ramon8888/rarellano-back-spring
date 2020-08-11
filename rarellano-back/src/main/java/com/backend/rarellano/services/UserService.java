package com.backend.rarellano.services;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.rarellano.converters.UserConverter;
import com.backend.rarellano.dto.UserDTO;
import com.backend.rarellano.entities.User;
import com.backend.rarellano.repositories.UserRepository;

@Service("userService")
public class UserService {
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Autowired
	@Qualifier("userConverter")
	private UserConverter userConverter;
	
	@Autowired
	private ModelMapper modelMapper;
	

	public static final Log logger = LogFactory.getLog(UserService.class);
	
	public List<UserDTO> obtain(){
		logger.info("< OK > USERS DATA CORRECTLY CONSULTED < OK >");
		return userConverter.userConverterList(userRepository.findAll());
	}
	public boolean create(User user) {
		logger.info("< ... > CREATING NEW USER < ... >");
		try {
			userRepository.save(user);
			logger.info("< OK > USER CREATED");
			return true;
		}catch(Exception e) {
			logger.warn("< ! > THERE WAS A PROBLEM DURING REGISTRATION");
			return false;
			
		}			
	}
	
	@Transactional(readOnly = true)
	public boolean UserExist(User user) {
		if (userRepository.existsByUser(user.getUser())) {
			return true;
		} else {
			return false;
		}
	}
	public Optional <User> getByUser(String user) {
		return userRepository.findByUser(user);
	}
	
	@Transactional(readOnly = true)
	public User findUsers(Long userDTO) {
		System.out.println("< INFO > THIS IS USER SEARCH" + userDTO);
		if (userDTO != null) {
			Optional<User> user = userRepository.findById(userDTO);
			User usere = user.get();
			// Se convierte Categoria a CategoriaDTO
			User userDto = modelMapper.map(usere, User.class);
			return userDto;
		} else {
			return null;
		}

	}
}
