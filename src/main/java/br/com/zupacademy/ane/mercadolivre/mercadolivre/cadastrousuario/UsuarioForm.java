package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.validacao.NotFuture;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.validacao.ValorUnico;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class UsuarioForm {


    @NotNull
    @NotFuture(domainClass = Usuario.class, fieldName = "instante")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    private LocalDate instante;
    @NotBlank @NotNull
    @Email @ValorUnico(domainClass = Usuario.class, fieldName = "login")
    private String login;
    @NotBlank @NotNull
    private String senha;

    public UsuarioForm() {
    }

    public UsuarioForm(LocalDate instante, String login, String senha) {
        this.instante = instante;
        this.login = login;
        this.senha = senha;
    }

    public LocalDate getInstante() {
        return instante;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
       return senha;
    }

    public Usuario converter(EntityManager manager) {
       return new Usuario(login, senha, instante);
    }

}
