package br.com.zupacademy.ane.mercadolivre.mercadolivre.detalheproduto;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastraproduto.Produto;

import java.math.BigDecimal;
import java.util.Set;
import java.util.SortedSet;

public class ListaDetalheProdutoDto {

    private String descricao;
    private String nome;
    private BigDecimal preco;
    private Set<DetalheProdutoCaracteristica> caracteristicas;
    private Set<String> linksImagens;
    private SortedSet<String> perguntas;

    public ListaDetalheProdutoDto(Produto produto) {
        this.descricao = produto.getDescricao();
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.caracteristicas = produto.mapeiaCaracteristica(DetalheProdutoCaracteristica::new);
        this.linksImagens = produto.mapeiaLinks(imagem -> imagem.getLink());
        this.perguntas = produto.mapeiaPerguntas(perguntas -> perguntas.getTitulo());
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Set<DetalheProdutoCaracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getLinksImagens() {
        return linksImagens;
    }

    public SortedSet<String> getPerguntas() {
        return perguntas;
    }
}