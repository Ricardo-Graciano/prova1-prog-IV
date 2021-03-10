package br.ueg.prova1ProgIV.dtos;

import org.springframework.security.core.userdetails.UserDetails;

import br.ueg.prova1ProgIV.models.User;

public class UserLoginResponseDTO {

	private String id;
	private String email;
	private String name;
	
	public UserLoginResponseDTO (User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.name = user.getName();
	}
	
	public UserLoginResponseDTO() {
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
