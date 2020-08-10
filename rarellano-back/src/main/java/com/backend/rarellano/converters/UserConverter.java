package com.backend.rarellano.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.backend.rarellano.dto.UserDTO;
import com.backend.rarellano.entities.User;

@Component("userConverter")
public class UserConverter {

	public List<UserDTO> userConverterList(List<User> users) {
		List<UserDTO> usersDTO = new ArrayList<>();

		for (User user : users)
			usersDTO.add(new UserDTO(user));

		return usersDTO;
	}
}
