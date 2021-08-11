package org.generation.blogPessoal.services;

import java.util.Optional;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.generation.blogPessoal.repository.TemaRepository;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostagemService {
    
    
    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
	private UsuarioRepository usuarioRepository;

    @Autowired
	private TemaRepository temaRepository;

	public Optional<?> cadastrarPostagem(Postagem novaPostagem) {
		Optional<Tema> objetoExistente = temaRepository.findById(novaPostagem.getTema().getId());
		return usuarioRepository.findById(novaPostagem.getCriador().getId()).map(usuarioExistente -> {
			if (objetoExistente.isPresent()) {
				novaPostagem.setCriador(usuarioExistente);
				novaPostagem.setTema(objetoExistente.get());
				return Optional.ofNullable(postagemRepository.save(novaPostagem));
			} else {
				return Optional.empty();
			}
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

	public Optional<Postagem> alterarPostagem(Postagem postagemParaAlterar) {
		return postagemRepository.findById(postagemParaAlterar.getId()).map(postagemExistente -> {
			postagemExistente.setTitulo(postagemParaAlterar.getTitulo());
			postagemExistente.setDescricao(postagemParaAlterar.getDescricao());
			return Optional.ofNullable(postagemRepository.save(postagemExistente));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}
}
