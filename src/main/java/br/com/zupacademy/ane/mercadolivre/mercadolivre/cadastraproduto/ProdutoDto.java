package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastraproduto;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrocategoria.Categoria;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProdutoDto {

    private String nome;
    private BigDecimal valor;
    private Long quantidade;
    private LocalDateTime instante;
    private Categoria categoria;

    public ProdutoDto() {
    }

    public ProdutoDto(Produto produto) {
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.quantidade = produto.getQuantidade();
        this.instante = produto.getInstante();
        this.categoria = produto.getCategoria();

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

    public Categoria getCategoria() {
        return categoria;
    }
}
