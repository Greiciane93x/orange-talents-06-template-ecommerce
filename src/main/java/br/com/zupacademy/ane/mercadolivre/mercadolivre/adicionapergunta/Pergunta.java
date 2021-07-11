package br.com.zupacademy.ane.mercadolivre.mercadolivre.adicionapergunta;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastraproduto.Produto;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.Usuario;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotNull @Valid
    @ManyToOne
    private Usuario usuarioInteressado;

    @NotNull @Valid
    @ManyToOne
    private Produto produto;

    private LocalDate instante;

    public Pergunta(@NotBlank String titulo,
                    @NotNull @Valid Usuario usuarioInteressado,
                    @NotNull @Valid Produto produto) {

                    this.titulo = titulo;
                    this.usuarioInteressado = usuarioInteressado;
                    this.produto = produto;
                    this.instante = LocalDate.now();
    }

    public Pergunta() {
    }

    public String getTitulo() {
        return titulo;
    }

    public Usuario getQuemPerguntou(){
        return usuarioInteressado;
    }

    public Usuario getDonoProduto() {
        return produto.getUsuarioAuth();
    }
}
