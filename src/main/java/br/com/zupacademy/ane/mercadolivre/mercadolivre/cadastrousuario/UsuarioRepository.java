package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.autenticausuario.UsuarioLogado;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<UsuarioLogado, Long> {

    Optional<UsuarioLogado> findByLogin(String login);
}
