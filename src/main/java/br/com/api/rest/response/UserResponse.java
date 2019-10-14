package br.com.api.rest.response;

public class UserResponse {

	private Long id;

	private String nome;

	private String login;

	private String email;

	private String senha;
	
	private StringBuilder erros;

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

	public String getErros() {
		if (this.erros == null) {
			return null;
		}
		
		return erros.toString();
	}

	public void setErros(String erro) {
		if (this.erros == null) {
			this.erros = new StringBuilder();
		}
		
		this.erros.append(erro);
		this.erros.append("\n");
	}

}
