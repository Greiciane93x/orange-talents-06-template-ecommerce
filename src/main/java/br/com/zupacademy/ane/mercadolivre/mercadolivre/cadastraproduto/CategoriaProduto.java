package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastraproduto;
import javax.persistence.*;

@Entity
public class CategoriaProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;

    @Deprecated
    public CategoriaProduto() {
    }

    public CategoriaProduto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
