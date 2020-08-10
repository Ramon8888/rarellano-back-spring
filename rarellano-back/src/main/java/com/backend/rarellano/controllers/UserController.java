package com.backend.rarellano.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.rarellano.dto.UserDTO;
import com.backend.rarellano.entities.User;
import com.backend.rarellano.services.UserService;

import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("cat/user")
public class UserController {
	
	//private static final Logger logger = LoggerFactory.getLogger();

	@Autowired
	private UserService userService;

	@PostMapping("/create")
	public ResponseEntity<?> agregarCategoria(@RequestBody @Valid User user) {
		Map<String, Object> response = new HashMap<>();
		//logger.debug("< INFO > YOU ARE CREATING A NEW USER");
		System.out.println("< INFO > CREATING...");
		if (!userService.UserExist(user)) {

			try {
				user.setCreatedAt(new Date());
				//user.setEstatus(true);
				userService.create(user);
				response.put("< INFO > YOU HAVE CREATED A NEW USER: ", user.toString());
				return ResponseEntity.ok(response);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body(new String("< ERROR > A PROBLEM OCURRED"));

			}
		} else {
			response.put("< ERROR > ALREADY EXISTS THIS USER: ", new String(""));
			response.put("< ERROR > ==>", user.toString());
			return ResponseEntity.badRequest().body(response);
		}

	}

	@PostMapping("/update/{id}")
//	@PreAuthorize("hasRole('Administrador')")
	public ResponseEntity<?> updateUser(@RequestBody @Valid User user, @PathVariable("id") Long id) {
		//logger.debug("< INFO > YOU ARE UPDATING THE USER: " + id);
		System.out.println("< INFO > UPDATING..." + id);
		Map<String, Object> response = new HashMap<>();
		try {
			User userUpdated = userService.findUsers(id);
			//System.out.println(cantidadPermitidaActualizada);
			userUpdated.setUser(user.getUser());
			userUpdated.setPassword(user.getPassword());
			userUpdated.setProject(user.getProject());
			userUpdated.setRoll(user.getRoll());
			userUpdated.setEmail(user.getEmail());
			userUpdated.setPhone(user.getPhone());
			userUpdated.setArea(user.getArea());
			userUpdated.setPosition(user.getPosition());
			userUpdated.setCreatedBy(user.getCreatedBy());
			userUpdated.setUpdatedBy(user.getUpdatedBy());
			userUpdated.setCreatedAt(new Date());
			userService.create(userUpdated);
			response.put("Menssage: ", new String("User updated"));
			//response.put("Cantidad Permitida: ", cantidadPermitidaActualizada);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.put("Menssage: ", new String("Error: error in update"));
			response.put("User : ", user.toString());
			return ResponseEntity.badRequest().body(response);

		}

	}
	

	@PostMapping("/elimina/{id}")
//	@PreAuthorize("hasRole('Administrador')")
	public ResponseEntity<?> deleteUser(@RequestBody @Valid User user, @PathVariable("id") Long id) {
		//logger.debug("Id para eliminar: " + id);
		System.out.println("Entro a eliminar " + id);
		Map<String, Object> response = new HashMap<>();
		try {
			User userDeleted = userService.findUsers(id);
			//System.out.println(cantidadPermitidaActualizada);
			//userDeleted.setEstatus(false);
			userDeleted.setUpdatedAt(new Date());
			userService.create(userDeleted);
			response.put("Mensaje: ", new String("Categoría Eliminada"));
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.put("Mensaje", new String("Error: error al eliminar categoría"));
			response.put("Categoría: ", user.toString());
			return ResponseEntity.badRequest().body(response);

		}

	}
	 

	@Autowired
	@Qualifier("userService")
	UserService service;
	
	@ApiOperation(value = "Listar categorías",
			notes = "Este servicio no requiere autenticación")
	@GetMapping("/users")
	public List<UserDTO> obtainUsers(){
		return userService.obtain();
	}

}
