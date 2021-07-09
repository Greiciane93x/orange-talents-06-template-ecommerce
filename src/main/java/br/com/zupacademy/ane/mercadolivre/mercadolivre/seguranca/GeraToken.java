package br.com.zupacademy.ane.mercadolivre.mercadolivre.seguranca;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class GeraToken {

    private static final long expirationTime = 180000;
    private String key = "Nane&123";
    public String geraToken(Optional<Usuario> usuarioLogado) {

        return Jwts.builder()
                .setIssuedAt(
                        new Date(System.currentTimeMillis()))
                        .setSubject("Test")
                        .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                        .signWith(SignatureAlgorithm.HS256, key)
                        .compact();

    }
}