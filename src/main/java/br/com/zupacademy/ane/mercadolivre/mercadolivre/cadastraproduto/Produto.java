package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastraproduto;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrocategoria.Categoria;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.Usuario;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
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

        Assert.isTrue(this.caracteristicas.size() >= 3,
                "Produto deve ter no mínimo 3 características");
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

    public Set<CaracteristicaProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<ImagemProduto> getImages() {
        return images;
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


}
