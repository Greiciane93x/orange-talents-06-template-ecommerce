package br.com.zupacademy.ane.mercadolivre.mercadolivre.detalheproduto;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastraproduto.Produto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
public class DetalheProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping(value = "/auth/produto/{id}")
    public ResponseEntity<ListaDetalheProdutoDto> listaDetalheProduto(@PathVariable("id") Long id){
        Produto produto = manager.find(Produto.class, id);
        // Na minha base o único produto que existe é o id 1,
        // Por isso fiz uma tratativa caso não seja esse id retornar notfound
        if(id != 1) {
            return ResponseEntity.notFound().build();
        }
            return ResponseEntity.ok().body(new ListaDetalheProdutoDto(produto));
    }
}
