package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastraproduto;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.validacao.ExistsId;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ProdutoForm {

    @NotNull @NotBlank
    private String nome;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull
    @Positive
    private Long quantidade;
    @Length(max = 1000)
    private String descricao;
    @NotNull
    @ExistsId(domainClass = CategoriaProduto.class, fieldName = "id")
    private Long idCategoriaProduto;

    public ProdutoForm(@NotNull @NotBlank  String nome, @NotNull @Positive BigDecimal valor, @NotNull @Positive Long quantidade, @Length(max=1000) String descricao,
                       @NotNull  Long idCategoriaProduto) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.idCategoriaProduto = idCategoriaProduto;
    }
    public ProdutoForm() {
    }


    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public Long getIdCategoriaProduto() {
        return idCategoriaProduto;
    }

    public Produto converter(EntityManager manager, Usuario usuarioAuthCadastroProduto) {
        @NotNull @NotBlank  CategoriaProduto categoria = manager.find(CategoriaProduto.class, idCategoriaProduto);
        return new Produto(nome, valor, quantidade, descricao, categoria, usuarioAuthCadastroProduto);
    }
}
