package br.com.zupacademy.ane.mercadolivre.mercadolivre.autenticausuario;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.UsuarioForm;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.UsuarioSenhaLimpa;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.seguranca.GerenciadorDeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    private UsuarioRepository usuarioRepository;

    private GerenciadorDeToken gerenciadorDeToken;

    private AuthenticationManager authenticationManager;

    @Autowired
    public TokenController(UsuarioRepository usuarioRepository, GerenciadorDeToken gerenciadorDeToken, AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.gerenciadorDeToken = gerenciadorDeToken;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value = "/token")
    public ResponseEntity<String> criaToken(@RequestBody TokenForm form){
        Authentication authenticate = authenticationManager.authenticate(form.geraAuthentication());
        String token = gerenciadorDeToken.geraToken(authenticate);
        return ResponseEntity.ok(token);
    }

}
