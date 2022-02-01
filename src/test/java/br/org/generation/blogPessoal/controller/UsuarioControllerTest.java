package br.org.generation.blogPessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.generation.blogPessoal.models.Usuario;
import org.generation.blogPessoal.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioControllerTest {
	
	@Autowired
	public TestRestTemplate testRestTemplate;
	
	@Autowired
	public UsuarioService usuarioService;
	
	@Test
	@Order(1)
	@DisplayName("Cadastrar um novo usuario")
	public void iraCadastrarUmUsuario() {
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(new Usuario(0L,
				"Paulo Reis","pauloreis@email.com","452545878"));
		
		ResponseEntity<Usuario> response = testRestTemplate
				.exchange("/usuarios/cadastrar", HttpMethod.POST, requisicao, Usuario.class);
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(requisicao.getBody().getNome(), response.getBody().getNome());
		assertEquals(requisicao.getBody().getUsuario(), response.getBody().getUsuario());
	}
	
	@Test
	@Order(2)
	@DisplayName("Não pode ter Usuario duplicado")
	public void naoPodeDuplicarUsuario() {
		
		usuarioService.cadastrarUsuario(new Usuario(0L,
				"Naty Reis","natyreis@email.com","452545878"));
		
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(new Usuario(0L,
				"Naty Reis","natyreis@email.com","452545878"));
		
		ResponseEntity<Usuario> response = testRestTemplate
				.exchange("/usuarios/cadastrar", HttpMethod.POST, requisicao, Usuario.class);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	@Order(3)
	@DisplayName("Alterar Usuario")
	public void deveAtualizarUmUsuario() {
		
		Optional<Usuario> usuarioCreate = usuarioService.cadastrarUsuario(new Usuario(0L,
				"Darllan Reis", "darllanreis@email.com", "darlan1234"));
		
		Usuario usuarioUpdate = new Usuario(usuarioCreate.get().getId(),
				"Darllan Boaz Reis", "darllanboaz@email.com", "darlan1234");
		
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(usuarioUpdate);
		
		ResponseEntity<Usuario> response = testRestTemplate
				.withBasicAuth("root", "root")
				.exchange("/usuarios/atualizar", HttpMethod.PUT, requisicao, Usuario.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(usuarioUpdate.getNome(), response.getBody().getNome());
		assertEquals(usuarioUpdate.getUsuario(), response.getBody().getUsuario());
		
	}
	
	@Test
	@Order(4)
	@DisplayName("Listar todos Usuarios")
	public void deveListarTodosUsuario() {
		
		usuarioService.cadastrarUsuario(new Usuario(0L,
				"Nanda Reis","nandareis@email.com","nanda12345"));
		
		usuarioService.cadastrarUsuario(new Usuario(0L,
				"Fernando Reis","fernandoreis@email.com","fernando123"));
		ResponseEntity<String> response = testRestTemplate
				.withBasicAuth("root", "root")
				.exchange("/usuarios/all", HttpMethod.GET, null, String.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	
	
}
