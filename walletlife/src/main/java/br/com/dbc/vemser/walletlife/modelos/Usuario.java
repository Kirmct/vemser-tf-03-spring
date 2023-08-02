package br.com.dbc.vemser.walletlife.modelos;

import java.time.LocalDate;

public class Usuario {

    private Integer id;

    private String nomeCompleto;

    private LocalDate dataNascimento;

    private String cpf;

    private String email;

    private String senha;

    public Usuario() {
    }

    public Usuario(String nomeCompleto, LocalDate dataNascimento, String cpf, String email, String senha) {
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return String.format("""
                Nome: %s
                Data de nascimento: %s
                CPF: %s
                Email: %s
                Senha: %s""", getNomeCompleto(), getDataNascimento(), getCpf(), getEmail(), getSenha());
    }
}
