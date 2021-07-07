package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String login;
    @Size(min = 6)
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false)
    private LocalDate instante;


    public Usuario() {
    }

    public Usuario(String login, String senha, LocalDate instante) {
        senha= Base64.encodeBase64String(senha.getBytes());
        this.login = login;
        this.senha = senha;
        this.instante = instante;
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
