package org.generation.blogPessoal.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Necessario Nome")
    private String nome;

	@Email(message = "Necessario Email")
    private String email;
    
    @Size(min = 5, max = 100, message = "Necessario min 5 caracteres")
    private String senha;

    @OneToMany(mappedBy = "criador", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"criador"})
	private List<Postagem> listaPostagem = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String senha, List<Postagem> listaPostagem) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.listaPostagem = listaPostagem;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Postagem> getListaPostagem() {
        return this.listaPostagem;
    }

    public void setListaPostagem(List<Postagem> listaPostagem) {
        this.listaPostagem = listaPostagem;
    }

}
