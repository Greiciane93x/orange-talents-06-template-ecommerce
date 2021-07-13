package br.com.zupacademy.ane.mercadolivre.mercadolivre.seguranca;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.Usuario;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class GerenciadorDeToken {

    private static final long expirationTime = 180000;
    private String key = "Nane&123";
    public String geraToken(Authentication authentication) {
    Usuario usuario = (Usuario) authentication.getPrincipal();
        return Jwts
                    .builder()
                    .setAudience(usuario.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                    .signWith(SignatureAlgorithm.HS256, key)
                    .compact();
    }

    public boolean verificaSeEhValido(String token) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | SignatureException ex) {
            return false;
        } catch (JwtException ex){
             return false;
        }
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .getAudience();
    }

}