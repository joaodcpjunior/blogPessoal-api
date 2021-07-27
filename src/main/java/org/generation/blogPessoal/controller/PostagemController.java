package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/postagem")
@CrossOrigin("*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;
	
	@GetMapping(value = "/buscar_tudo")
	public ResponseEntity<List<Postagem>> findAllPostagem() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Postagem> findByIdPostagem(@PathVariable Long id) {
		return ResponseEntity.ok(repository.findById(id).get());
	}
	
	@GetMapping(value = "/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<Postagem> post (@RequestBody Postagem postagem) {
		return ResponseEntity.status(201).body(repository.save(postagem));		
	}
	
	@PutMapping
	public ResponseEntity<Postagem> put (@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));		
	}
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	
}
