package org.generation.blogPessoal.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_postagem")
public class Postagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 45, message = "Maximo 45 caracteres")
	private String titulo;

	@NotBlank
	@Size(max = 300, message = "Maximo 300 caracteres")
	private String descricao;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataPostagem = LocalDate.now();

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnoreProperties({ "listaPostagem" })
	private Usuario criador;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnoreProperties({ "listaDePostagens" })
	private Tema tema;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataPostagem() {
		return this.dataPostagem;
	}

	public void setDataPostagem(LocalDate dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	public Usuario getCriador() {
		return this.criador;
	}

	public void setCriador(Usuario criador) {
		this.criador = criador;
	}

	public Tema getTema() {
		return this.tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}
	
}
