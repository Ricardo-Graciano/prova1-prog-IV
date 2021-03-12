package br.ueg.prova1ProgIV.dtos;

import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

@Validated
public class UserLoginRequestDTO {

	@NotBlank(message = "É necessário informar um EMAIL")
	private String email;
	@NotBlank(message = "É necessário informar uma SENHA")
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
