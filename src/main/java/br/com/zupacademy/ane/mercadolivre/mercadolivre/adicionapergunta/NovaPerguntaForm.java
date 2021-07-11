package br.com.zupacademy.ane.mercadolivre.mercadolivre.adicionapergunta;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastraproduto.Produto;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.Usuario;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaPerguntaForm {

    @NotBlank
    private String titulo;

    public NovaPerguntaForm(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public NovaPerguntaForm() {
    }

    public Pergunta converter(@NotNull @Valid Usuario usuarioInteressado, Produto produto) {
        return new Pergunta(titulo, usuarioInteressado, produto);
    }
}
