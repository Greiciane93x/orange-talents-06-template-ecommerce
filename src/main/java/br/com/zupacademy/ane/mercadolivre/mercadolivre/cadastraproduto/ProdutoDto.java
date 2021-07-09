package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastraproduto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class ProdutoDto {

    private String nome;
    private BigDecimal valor;
    private Long quantidade;
    private LocalDateTime instante;
    private Set<Caracteristica> caracteristica;
    private CategoriaProduto categoriaProduto;

    public ProdutoDto() {
    }

    public ProdutoDto(Produto produto) {
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.quantidade = produto.getQuantidade();
        this.instante = produto.getInstante();
        this.caracteristica = produto.getCaracteristica();
        this.categoriaProduto = produto.getCategoria();

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

    public LocalDateTime getInstante() {
        return instante;
    }

    public Set<Caracteristica> getCaracteristica() {
        return caracteristica;
    }

    public CategoriaProduto getCategoriaProduto() {
        return categoriaProduto;
    }
}
