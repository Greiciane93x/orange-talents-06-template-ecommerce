package br.com.zupacademy.ane.mercadolivre.mercadolivre.autenticausuario;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.*;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.seguranca.GeraToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class AutenticaUsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GeraToken geraToken;

    public AutenticaUsuarioController( UsuarioRepository usuarioRepository, GeraToken geraToken){
        this.usuarioRepository = usuarioRepository;
        this.geraToken = geraToken;
    }

    @PostMapping(value = "/auth")
    @Transactional
    public ResponseEntity<UsuarioDto> autenticaUsuario(@RequestBody @Valid UsuarioLogado usuarioLogado)  {
        Optional<Usuario> usuarioLogar = usuarioRepository.findByLogin(usuarioLogado.getLogin());
        Usuario usuario = usuarioLogado.converter(usuarioRepository);
        if(usuarioLogar.isPresent()) {
            String token = geraToken.geraToken(usuarioLogar);
            System.out.println(token);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}
