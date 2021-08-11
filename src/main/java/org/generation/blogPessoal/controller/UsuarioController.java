package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.dtos.UsuarioDTO;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.generation.blogPessoal.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuario")
@CrossOrigin("*")
public class UsuarioController {
    
    @Autowired 
    private UsuarioRepository repository;

    @Autowired 
	private UsuarioService service;

    @PostMapping("/salvar")
	public ResponseEntity<Object> cadastrarUsuario(@Valid @RequestBody Usuario novoUsuario) {
		Optional<Object> objetoCadastrado = service.cadastrarUsuario(novoUsuario);

		if (objetoCadastrado.isPresent()) {
			return ResponseEntity.status(201).body(objetoCadastrado.get());
		} else {
			return ResponseEntity.status(400).build();
		}

	}

	@PutMapping("/autenticar")
	public ResponseEntity<Object> pegarCredenciais(@Valid @RequestBody UsuarioDTO loginSenha) {
		Optional<?> objetoCredenciado = service.pegarCredenciais(loginSenha);

		if (objetoCredenciado.isPresent()) {
			return ResponseEntity.status(201).body(objetoCredenciado.get());
		} else {
			return ResponseEntity.status(400).build();
		}

	}

	@GetMapping("/todes")
	public ResponseEntity<Object> buscarTodes() {
		List<Usuario> listaUsuarios = repository.findAll();

		if (listaUsuarios.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaUsuarios);
		}

	}

	@GetMapping("/{id_usuario}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable(value = "id_usuario") Long id) {
		Optional<Usuario> objetoUsuario = repository.findById(id);
		if (objetoUsuario.isPresent()) {
			return ResponseEntity.status(200).body(objetoUsuario.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	@GetMapping("/pesquisa")
	public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(defaultValue = "") String nome) {
		return ResponseEntity.status(200).body(repository.findAllByNomeContainingIgnoreCase(nome));
	}

	@PutMapping("/alterar")
	public ResponseEntity<Object> alterar(@Valid @RequestBody UsuarioDTO usuarioParaAlterar) {
		Optional<?> objetoAlterado = service.alterarUsuario(usuarioParaAlterar);

		if (objetoAlterado.isPresent()) {
			return ResponseEntity.status(201).body(objetoAlterado.get());
		} else {
			return ResponseEntity.status(400).build();
		}
	}

	@DeleteMapping("/deletar/{id_usuario}")
	public ResponseEntity<Object> deletarPorId(@PathVariable(value = "id_usuario") Long idUsuario) {
		Optional<Usuario> objetoExistente = repository.findById(idUsuario);
		if (objetoExistente.isPresent()) {
			repository.deleteById(idUsuario);
			return ResponseEntity.status(200).build();
		} else {
			return ResponseEntity.status(400).build();
		}
	}
    
}
