package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastraproduto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private BigDecimal valor;
    @Column(nullable = false)
    private Long quantidade;
    private LocalDateTime instante;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "caracteristica_id")
    private Set<Caracteristica> caracteristica;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_id")
    private CategoriaProduto categoria;

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, BigDecimal valor, Long quantidade, Set<Caracteristica> caracteristica, CategoriaProduto categoria) {
        this.nome = nome;
        if(valor.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException();
        }
        this.valor = valor;
        if(quantidade <=0){
            throw new IllegalArgumentException();
        }
        this.quantidade = quantidade;
        this.instante = instante.now();
        this.caracteristica = caracteristica;
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

    public Set<Caracteristica> getCaracteristica() {
        return caracteristica;
    }

    public CategoriaProduto getCategoria() {
        return categoria;
    }
}
