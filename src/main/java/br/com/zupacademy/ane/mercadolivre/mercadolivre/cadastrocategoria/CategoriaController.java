 package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrocategoria;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
public class CategoriaController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping(value= "/categoria")
    @Transactional
    public ResponseEntity<CategoriaDto> criarCategoria(@RequestBody @Valid CategoriaForm form){
        Categoria categoria = form.converter(manager) ;
        manager.persist(categoria);
        return ResponseEntity.ok().build();
    }

}
