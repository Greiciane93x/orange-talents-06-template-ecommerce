package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastraproduto;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.autenticausuario.UsuarioLogado;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.autenticausuario.UsuarioRepository;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ProdutoController(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    @PostMapping("/auth/produto")
    public ResponseEntity<ProdutoDto> criarProduto(@RequestBody @Valid ProdutoForm form, UsuarioLogado usuarioLogado){
       Usuario usuarioAuthCadastroProduto = usuarioRepository.findByLogin("aneassis@logado").get();

                Produto produto = form.converter(manager,usuarioAuthCadastroProduto);
                manager.persist(produto);
                return ResponseEntity.ok().body(new ProdutoDto(produto));
        }

}
