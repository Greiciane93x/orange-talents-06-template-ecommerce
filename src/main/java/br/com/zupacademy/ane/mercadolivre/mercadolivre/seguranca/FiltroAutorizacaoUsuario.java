package br.com.zupacademy.ane.mercadolivre.mercadolivre.seguranca;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.autenticausuario.UsuarioRepository;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.UsuarioSenhaLimpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class FiltroAutorizacaoUsuario extends OncePerRequestFilter {


    private GerenciadorDeToken gerenciadorDeToken;
    private UsuarioRepository repository;

    @Autowired
    public FiltroAutorizacaoUsuario(GerenciadorDeToken gerenciadorDeToken, UsuarioRepository repository) {
        this.gerenciadorDeToken = gerenciadorDeToken;
        this.repository = repository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if(Objects.nonNull(authorization) && !authorization.isEmpty()){
            if(gerenciadorDeToken.verificaSeEhValido(authorization)){
                String username = gerenciadorDeToken.getUsername(authorization);

                Usuario usuario =  new Usuario(username);
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(token);
            };
        }
         filterChain.doFilter(request, response);

    }


}
