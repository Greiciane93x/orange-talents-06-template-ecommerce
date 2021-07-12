package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastraproduto;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.adicionapergunta.Pergunta;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrocategoria.Categoria;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.detalheproduto.DetalheProdutoCaracteristica;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    @Positive
    private BigDecimal valor;
    @Column(nullable = false)
    @Positive
    private Long quantidade;
    @Column(nullable = false, unique = false)
    private String descricao;
    private LocalDateTime instante;

    @ManyToOne
    private Categoria categoria;

    @Valid @NotNull @ManyToOne
    private Usuario usuarioAuth;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> images = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    @OrderBy("titulo asc")
    private SortedSet<Pergunta> perguntas = new TreeSet<>();

    @Deprecated
    public Produto() {
    }

    public Produto(@NotNull @NotBlank String nome, @NotNull @Positive BigDecimal valor,
                   @NotNull @Positive Long quantidade, @NotBlank @Length(max = 1000) String descricao,
                   @NotNull @Valid Categoria categoria, @NotNull @Valid Usuario usuarioAuth,
                   @Valid @Size(min = 3) List<NovaCaracteristicaForm> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.instante = instante.now();
        this.categoria = categoria;
        this.usuarioAuth = usuarioAuth;
        this.images = images;
        this.caracteristicas.addAll(caracteristicas.stream()
        .map(caracterisca -> caracterisca.converter(this))
        .collect(Collectors.toSet()));

    }

    public void vinculaImgs(Set<String> links) {
        Set<ImagemProduto> imagens = links.stream()
                .map(link -> new ImagemProduto(this, link))
                .collect(Collectors.toSet());

        this.images.addAll(imagens);
    }
    public boolean isOwner(Usuario usuarioDono) {
        return this.usuarioAuth.equals(usuarioDono);
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

    public String getDescricao() { return descricao; }

    public Categoria getCategoria() {
        return categoria;
    }

    public Usuario getUsuarioAuth() {
        return usuarioAuth;
    }

    public Set<Pergunta> getPerguntas() {
        return perguntas;
    }

    public Set<CaracteristicaProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<DetalheProdutoCaracteristica> mapeiaCaracteristica(Function<CaracteristicaProduto, DetalheProdutoCaracteristica> funcaoMapeadora){
        return this.caracteristicas.stream().map(funcaoMapeadora).collect(Collectors.toSet());
    }
    public <T>Set<T> mapeiaLinks(Function<ImagemProduto, T> funcaoMapeadora){
        return this.images.stream().map(funcaoMapeadora).collect(Collectors.toSet());
    }
    public <T extends Comparable<T>> SortedSet<T> mapeiaPerguntas(Function<Pergunta, T> funcaoMapeadora){
        return this.perguntas.stream().map(funcaoMapeadora).collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id) && Objects.equals(nome, produto.nome) && Objects.equals(valor, produto.valor) && Objects.equals(quantidade, produto.quantidade) && Objects.equals(descricao, produto.descricao) && Objects.equals(instante, produto.instante) && Objects.equals(categoria, produto.categoria) && Objects.equals(usuarioAuth, produto.usuarioAuth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, valor, quantidade, descricao, instante, categoria, usuarioAuth);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", instante=" + instante +
                ", categoria=" + categoria +
                ", usuarioAuth=" + usuarioAuth +
                ", caracteristicas=" + caracteristicas +
                ", images=" + images +
                '}';
    }

    // o retorno aqui é boleano indicando que pode ser
    // caso dos itens(produtos) do estoque não existirem
    public boolean abataEstoque(@Positive int quantidade) {
        Assert.isTrue(quantidade > 0, "A quantidade deve ser maior do que zero" + quantidade);
        if(quantidade <= this.quantidade){
            this.quantidade -= quantidade;
            return true;
        }
        return false;
    }
}
