package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrocategoria;

import javax.persistence.*;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_mae_id")
    private CategoriaMae categoriaMae;

    public Categoria() {
    }

    public Categoria(String nome, CategoriaMae categoriaMae) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }

    public String getNome() {
        return nome;
    }

    public CategoriaMae getCategoriaMae() {
        return categoriaMae;
    }
}
