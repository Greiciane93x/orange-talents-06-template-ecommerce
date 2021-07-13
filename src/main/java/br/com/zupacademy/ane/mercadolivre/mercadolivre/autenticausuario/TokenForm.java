package br.com.zupacademy.ane.mercadolivre.mercadolivre.autenticausuario;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.UsuarioSenhaLimpa;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class TokenForm {
    private String login, senha;

    public TokenForm(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public TokenForm() {
    }

    public String getSenha() { return senha; }

    public String getLogin() {
        return login;
    }

    public Authentication geraAuthentication() {
        return new UsernamePasswordAuthenticationToken(this.login, this.senha);
    }

    @Override
    public String toString() {
        return "TokenForm{" +
                "login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
