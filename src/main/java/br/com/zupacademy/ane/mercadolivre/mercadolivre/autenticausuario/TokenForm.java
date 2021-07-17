package br.com.zupacademy.ane.mercadolivre.mercadolivre.autenticausuario;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TokenForm {
    private String login, senha;

    public TokenForm(String login, String senha) {
        this.login = login;
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }

    public TokenForm() {
    }

    public String getSenha() { return senha; }

    public String getLogin() {
        return login;
    }

    public Authentication geraAuthentication() {
        return new UsernamePasswordAuthenticationToken(this.login, new BCryptPasswordEncoder().encode(senha));
    }

    @Override
    public String toString() {
        return "TokenForm{" +
                "login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
