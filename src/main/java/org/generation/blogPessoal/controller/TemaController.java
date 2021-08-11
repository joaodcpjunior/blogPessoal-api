package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;
import org.generation.blogPessoal.services.TemaService;
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
@RequestMapping(value = "/api/v1/tema")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {

	@Autowired 
	private TemaRepository temaRepository;

	@Autowired 
	private TemaService temaService;

	@PostMapping("/salvar")
	public ResponseEntity<Object> cadastrarPostagem(@Valid @RequestBody Tema novoTema) {
		return ResponseEntity.status(201).body(temaRepository.save(novoTema));
	}

	@GetMapping("/todos")
	public ResponseEntity<Object> buscarTodos() {
		List<Tema> listaTema = temaRepository.findAll();

		if (listaTema.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaTema);
		}

	}

	@GetMapping("/{id_tema}")
	public ResponseEntity<Tema> buscarPorId(@PathVariable(value = "id_tema") Long id) {
		Optional<Tema> objetoTema = temaRepository.findById(id);
		if (objetoTema.isPresent()) {
			return ResponseEntity.status(200).body(objetoTema.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	@GetMapping("/pesquisa")
	public ResponseEntity<List<Tema>> buscarPorTema(@RequestParam(defaultValue = "") String tema) {
		return ResponseEntity.status(200).body(temaRepository.findAllByTemaContainingIgnoreCase(tema));
	}

	@PutMapping("/alterar")
	public ResponseEntity<Object> alterar(@Valid @RequestBody Tema temaParaAlterar) {
		Optional<Tema> objetoAlterado = temaService.alterarTema(temaParaAlterar);

		if (objetoAlterado.isPresent()) {
			return ResponseEntity.status(201).body(objetoAlterado.get());
		} else {
			return ResponseEntity.status(400).build();
		}
	}

	@DeleteMapping("/deletar/{id_tema}")
	public ResponseEntity<Object> deletarPorId(@PathVariable(value = "id_tema") Long idTema) {
		Optional<Tema> objetoExistente = temaRepository.findById(idTema);
		if (objetoExistente.isPresent()) {
			temaRepository.deleteById(idTema);
			return ResponseEntity.status(200).build();
		} else {
			return ResponseEntity.status(400).build();
		}

	}
	
}
