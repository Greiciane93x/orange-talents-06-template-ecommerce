package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.validacao.ValorUnico;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class UsuarioForm {

    @NotBlank @NotNull
    @Email @ValorUnico(domainClass = Usuario.class, fieldName = "login")
    private String login;

    @NotBlank @NotNull
    private String senha;

    @NotNull
    private LocalDate instante;

    public UsuarioForm() {
    }

    public UsuarioForm(String login,LocalDate instante, String senha) {
        this.login = login;
        this.instante = instante.now();
        this.senha = new BCryptPasswordEncoder().encode(senha);

    }

    public String getLogin() {
        return login;
    }

    public String getSenha() { return senha; }

    public LocalDate getInstante() { return instante; }

    public Usuario converter(EntityManager manager) {
       return new Usuario(login, instante, new UsuarioSenhaLimpa(senha).hash());
    }

}
