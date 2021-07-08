package br.com.zupacademy.ane.mercadolivre.mercadolivre.autenticausuario;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.UsuarioDto;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.UsuarioForm;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.UsuarioRepository;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.seguranca.GeraToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
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
    public ResponseEntity<UsuarioDto> autenticaUsuario(@RequestBody UsuarioForm usuarioForm)  {
        Optional<UsuarioLogado> usuarioLogado = usuarioRepository.findByLogin(usuarioForm.getLogin());
        if(usuarioLogado.isPresent()) {
            String token = geraToken.geraToken(usuarioLogado);
            System.out.println(token);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
