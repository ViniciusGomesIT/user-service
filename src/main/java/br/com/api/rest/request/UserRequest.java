package br.com.api.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRequest {

	private Long id;
	
	@JsonProperty(value = "name")
	private String nome;

	@JsonProperty(value = "username")
	private String login;

	@JsonProperty(value = "email")
	private String email;

	@JsonProperty(value = "password")
	private String senha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
