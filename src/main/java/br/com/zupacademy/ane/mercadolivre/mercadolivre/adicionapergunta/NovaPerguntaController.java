 package br.com.zupacademy.ane.mercadolivre.mercadolivre.adicionapergunta;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.adicionaopiniao.OpiniaoDto;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.autenticausuario.UsuarioRepository;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastraproduto.Produto;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class NovaPerguntaController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Emails emails;

    @PostMapping(value = "auth/produtos/{id}/pergunta")
    @Transactional
    public ResponseEntity<PerguntaDto> criaOpinicao(@RequestBody @Valid NovaPerguntaForm form, @PathVariable("id") Long id) {
        Produto produto = manager.find(Produto.class,id);
        Usuario usuarioInteressado = usuarioRepository.findByLogin("ane@pleno").get();

        Pergunta novaPergunta = form.converter(usuarioInteressado, produto);
        manager.persist(novaPergunta);
        emails.novaPergunta(novaPergunta);

        return ResponseEntity.ok(new PerguntaDto(novaPergunta));
    }


}
