package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrocategoria;

import javax.persistence.*;

@Entity
public class CategoriaMae {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public CategoriaMae() {
    }


    public CategoriaMae(String nome){
        this.nome = nome;
    }


    public String getNome() {
        return nome;
    }
}
