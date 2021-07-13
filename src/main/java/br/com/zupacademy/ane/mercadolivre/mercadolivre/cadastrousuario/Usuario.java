package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Collection;

@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Size(min = 6)  @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    @PastOrPresent
    private LocalDate instante;

    @Deprecated
    public Usuario() {
    }

    public Usuario(String username,LocalDate instante, String senhaLimpa) {
        this.login = username;
        this.senha = senhaLimpa;
        this.instante = instante.now();
    }

    public Usuario(String username) {
        this.login = username;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
