package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;



public class UsuarioSenhaLimpa {

    private String senha;

    @Deprecated
    public UsuarioSenhaLimpa() {
    }

    public UsuarioSenhaLimpa(String senha){

        Assert.hasLength(senha, "Sua senha não pode ser em branco!");
        Assert.isTrue(senha.length()>= 6, "Senha deve ter no mínimo 6 caracteres");
        this.senha = senha;

    }
    public String hash(){
        return new BCryptPasswordEncoder().encode(senha);
    }
}
