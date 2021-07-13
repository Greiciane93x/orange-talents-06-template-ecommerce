package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastraproduto;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrocategoria.Categoria;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.detalheproduto.DetalheProdutoCaracteristica;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoDto {

    private String nome;
    private BigDecimal valor;
    private Long quantidade;
    private LocalDateTime instante;
    private Categoria categoria;
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    public ProdutoDto() {
    }

    public ProdutoDto(Produto produto) {
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.categoria = produto.getCategoria();
        this.quantidade = produto.getQuantidade();
        this.instante = produto.getInstante();

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
