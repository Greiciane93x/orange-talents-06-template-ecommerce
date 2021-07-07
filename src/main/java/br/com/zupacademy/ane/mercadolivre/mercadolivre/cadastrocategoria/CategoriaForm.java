package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrocategoria;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.validacao.ExistsId;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.validacao.ValorUnico;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CategoriaForm {

    @NotBlank
    @ValorUnico(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    @ExistsId(domainClass = CategoriaMae.class, fieldName = "id")
    @Positive
    private Long idCategoriaMae;

    public CategoriaForm() {
    }

    public CategoriaForm(String nome, Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdCategoriaMae() {
        return idCategoriaMae;
    }

    public Categoria converter(EntityManager manager) {
        @NotNull CategoriaMae categoriaMae = manager.find(CategoriaMae.class, idCategoriaMae);
        return new Categoria(nome, categoriaMae);
    }
}

