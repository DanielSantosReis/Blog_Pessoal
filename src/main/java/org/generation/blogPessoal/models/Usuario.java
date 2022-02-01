package org.generation.blogPessoal.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table (name = "usuario")
public class Usuario {
	
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long  id;
	@Size(max = 80)
	private @NotBlank String nome;
	@Size(max = 80)
	private @NotBlank String usuario;
	@Size(max = 80)
	private @NotBlank String senha;
		
	public Usuario(long id, @Size(max = 80) @NotBlank String nome, @Size(max = 80) @NotBlank String usuario,
			@Size(max = 80) @NotBlank String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public Usuario() {	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
