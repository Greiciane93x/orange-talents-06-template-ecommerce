package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.validacao.NotFuture;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Usuario{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String login;
    @Size(min = 6)
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false)
    @PastOrPresent
    private LocalDate instante;

    public Usuario() {
    }

    public Usuario(String login, UsuarioSenhaLimpa senhaLimpaUsuario) {
        this.login = login;
        this.senha = senhaLimpaUsuario.hash();
        this.instante = instante.now();
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDate getInstante() {
        return instante;
    }

}
