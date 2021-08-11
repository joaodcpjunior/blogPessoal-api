package org.generation.blogPessoal.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class UsuarioDTO {
    
    private Long id;

    private String nome;

    @Email(message = "Necessario Email")
    private String email;

    @Size(min = 5, max = 100, message = "Necessario min 5 caracteres")
    private String senha;
    
    private String token;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nome, String email, String senha, String token) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.token = token;
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

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
