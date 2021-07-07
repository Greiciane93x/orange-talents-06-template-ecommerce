package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class UsuarioController {

    @PersistenceContext
    private EntityManager manager;


    @PostMapping(value="/usuario")
    @Transactional
    public void cadastrar(@RequestBody @Valid UsuarioForm form){
        Usuario usuario = form.converter(manager);
        manager.persist(usuario);

    }
}
