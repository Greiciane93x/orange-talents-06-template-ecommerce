package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastraproduto;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.autenticausuario.UsuarioRepository;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@RestController
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Uploader uploaderFake;


    @Transactional
    @PostMapping(value="/auth/produto")
    public ResponseEntity<ProdutoDto> criarProduto(@RequestBody @Valid ProdutoForm form){
       Usuario usuarioAuthCadastroProduto = usuarioRepository.findByLogin("ane@test").get();

           Produto produto = form.converter(manager,usuarioAuthCadastroProduto);
           manager.persist(produto);
           return ResponseEntity.ok().body(new ProdutoDto(produto));

    }

    @PostMapping(value = "/auth/produto/{id}/images")
    @Transactional
    public String adicionaImg(@PathVariable("id") Long id, @Valid NovasImgsForm form){

        Usuario usuarioDono = usuarioRepository.findByLogin("ane@teste").get();
        Produto produto = manager.find(Produto.class, id);

        if(!produto.isOwner(usuarioDono)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        Set<String> links = uploaderFake.send(form.getImgs());
        produto.vinculaImgs(links);

        manager.merge(produto);
        return produto.toString();
    }

}
