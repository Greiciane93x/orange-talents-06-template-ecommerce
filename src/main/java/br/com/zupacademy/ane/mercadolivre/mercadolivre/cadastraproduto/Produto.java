package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastraproduto;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    @Column(nullable = false)
    private String descricao;
    private LocalDateTime instante;

    @Valid
    @NotNull
    @ManyToOne
    private CategoriaProduto categoria;

    @Valid
    @NotNull
    @ManyToOne
    private Usuario usuarioAuth;

    @Deprecated
    public Produto() {
    }

    public Produto(@NotNull @NotBlank String nome, @NotNull @Positive BigDecimal valor,
                   @NotNull @Positive Long quantidade, @NotBlank @Length(max = 1000) String descricao,
                   @NotNull @Valid CategoriaProduto categoria, @NotNull @Valid Usuario usuarioAuth) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.instante = instante.now();
        this.categoria = categoria;
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

    public CategoriaProduto getCategoria() {
        return categoria;
    }
}
