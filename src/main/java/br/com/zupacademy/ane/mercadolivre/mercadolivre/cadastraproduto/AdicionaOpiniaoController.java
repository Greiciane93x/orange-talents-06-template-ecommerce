package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastraproduto;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.autenticausuario.UsuarioRepository;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class AdicionaOpiniaoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping(value = "/auth/produtos/{id}/opiniao",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<OpiniaoDto> criaOpiniao(@RequestBody  @Valid NovaOpiniaoForm form, @PathVariable("id") Long id){
        Produto produto = manager.find(Produto.class, id);
        Usuario consumidor = usuarioRepository.findByLogin("ane@pleno").get();
        Opiniao novaOpiniao = form.converter(produto, consumidor);
        manager.persist(novaOpiniao);

        return ResponseEntity.ok(new OpiniaoDto(novaOpiniao));
    }
}
