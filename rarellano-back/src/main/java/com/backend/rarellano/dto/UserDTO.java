package com.backend.rarellano.dto;

import java.io.Serializable;
import java.util.Date;

import com.backend.rarellano.entities.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserDTO implements Serializable {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2004147740698247792L;

	public UserDTO() {
			
		}
	private Long id;
	private String user;
	private String password;
	private Integer project;
	private Integer roll;
	private String email;	
	private String phone;
	private Integer area;
	private Integer position;
	private Integer createdBy;
	private Integer updatedBy;
	private Date createdAt;
	private Date updatedAt;
	private Boolean status;
	
	public UserDTO(User user) {
		
		this.id = user.getId();
		this.user = user.getUser();
		this.password = user.getPassword();
		this.project = user.getProject();
		this.roll = user.getRoll();
		this.email = user.getEmail();
		this.phone = user.getPhone();
		this.area = user.getArea();
		this.position = user.getPosition();
		this.createdBy = user.getCreatedBy();
		this.updatedBy = user.getUpdatedBy();
		this.createdAt = user.getCreatedAt();
		this.updatedAt = user.getUpdatedAt();
		this.status = user.getStatus();
	}

	public UserDTO(Long id, String user, String password, Integer project, Integer roll, String email, String phone,
			Integer area, Integer position, Integer createdBy, Integer updatedBy, Date createdAt, Date updatedAt, Boolean status) {
		this.id = id;
		this.user = user;
		this.password = password;
		this.project = project;
		this.roll = roll;
		this.email = email;
		this.phone = phone;
		this.area = area;
		this.position = position;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.status = status;
	}
	
}
