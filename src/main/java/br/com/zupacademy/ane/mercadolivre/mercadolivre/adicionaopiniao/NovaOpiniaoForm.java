package br.com.zupacademy.ane.mercadolivre.mercadolivre.adicionaopiniao;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastraproduto.Produto;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.Usuario;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class NovaOpiniaoForm {

    @Min(1) @Max(5)
    private int nota;

    @NotBlank
    private String titulo;

    @NotBlank @Size(max = 500)
    private String descricao;

    public NovaOpiniaoForm(@Min(1) @Max(5) int nota,
                           @NotBlank String titulo,
                           @NotBlank @Size(max = 500) String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public int getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Opiniao converter(@NotNull @Valid Produto produto, Usuario usuario) {
        return new Opiniao(nota, titulo, descricao, produto, usuario);
    }
}
