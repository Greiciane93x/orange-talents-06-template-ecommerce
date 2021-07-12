package br.com.zupacademy.ane.mercadolivre.mercadolivre.autenticausuario;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.UsuarioSenhaLimpa;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UsuarioLogado {

    @NotNull @NotBlank
    private String login;
    @NotNull @NotBlank
    private String senha;

    @Deprecated
    public UsuarioLogado() {
    }

    public UsuarioLogado(@NotBlank @NotNull String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario converter(UsuarioRepository usuarioRepository) {
        return new Usuario(login, new UsuarioSenhaLimpa(senha));
    }

}
