package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.validacao.ValorUnico;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UsuarioForm {

    @NotBlank @NotNull
    @Email @ValorUnico(domainClass = Usuario.class, fieldName = "login")
    private String login;
    @NotBlank @NotNull
    private String senha;


    public UsuarioForm() {
    }

    public UsuarioForm(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
       return senha;
    }

    public Usuario converter(EntityManager manager) {
       return new Usuario(login, new UsuarioSenhaLimpa(senha));
    }

}
