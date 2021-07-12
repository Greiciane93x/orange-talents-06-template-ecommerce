package br.com.zupacademy.ane.mercadolivre.mercadolivre.fechamentocompra;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.adicionapergunta.Emails;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.autenticausuario.UsuarioLogado;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.autenticausuario.UsuarioRepository;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastraproduto.Produto;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class FechamentoCompraController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Emails emails;


    @PostMapping(value = "auth/compras")
    @Transactional
    public  ResponseEntity<NovaCompraDto> fechaCompra(@RequestBody @Valid NovaCompraForm form,
                                                      UriComponentsBuilder uriComponentsBuilder){
        Produto produtoASerComprado = manager.find(Produto.class, form.getIdProduto());

        int quantidade = form.getQuantidade();
        boolean abateu = produtoASerComprado.abataEstoque(form.getQuantidade());

        if (abateu) {
            Usuario comprador = usuarioRepository.findByLogin("ane@test").get();
            GatewayPagamento gateway = form.getGateway();
            String statusCompra = form.getStatusCompra();
            Compra novaCompra = new Compra(produtoASerComprado, quantidade, comprador, gateway, statusCompra);
            manager.persist(novaCompra);
            return ResponseEntity.status(302).body(new NovaCompraDto(novaCompra.urlRedirecionamento(gateway,uriComponentsBuilder)));
        }
        return ResponseEntity.badRequest().build();
    }
}