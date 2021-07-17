package br.com.zupacademy.ane.mercadolivre.mercadolivre.autenticausuario;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.Usuario;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findByLogin(String login);

}