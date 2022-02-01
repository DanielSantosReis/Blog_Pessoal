package br.org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.models.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
		
	@Autowired
	public UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		
		usuarioRepository.save(new Usuario(01, "Daniel Reis","danielreis@gmail.com","44058955" ));
		
		usuarioRepository.save(new Usuario(01, "Glaucya Reis","glaucyahora@gmail.com","44058955" ));
		
		usuarioRepository.save(new Usuario(01, "Luiz Reis","luizpara@gmail.com","44058955" ));
		
		usuarioRepository.save(new Usuario(01, "Rebeca Marques","rebeca@gmail.com","44058955" ));
		
	}
	
	@Test
	@DisplayName("Retorna somente 1 usuario")
	public void deveRetornarUmUsuario() {
		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("danielreis@gmail.com");
		assertTrue(usuario.get().getUsuario().equals("danielreis@gmail.com"));
	}
	
	@Test
	@DisplayName("Retornará 3 usuarios")
	public void deveRetornarTresUsuarios() {
		
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Reis");
		assertEquals(3, listaDeUsuarios.size());
		
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Glaucya Reis"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Luiz Reis"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Daniel Reis"));
	}
	
	

}
