package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastraproduto;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrocategoria.Categoria;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.Usuario;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.validacao.ExistsId;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.validacao.ValorUnico;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProdutoForm {

    @NotNull @NotBlank
    @ValorUnico(domainClass = Produto.class, fieldName = "nome")
    private String nome;

    @NotNull @Positive
    private BigDecimal valor;

    @NotNull @Positive
    private Long quantidade;

    @Length(max = 1000)
    @ValorUnico(domainClass = Produto.class, fieldName = "descricao")
    private String descricao;

    @NotNull
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;

    @Valid @Size(min = 3)
    private List<NovaCaracteristicaForm> caracteristicas = new ArrayList<>();


    public ProdutoForm(@NotNull @NotBlank  String nome,
                       @NotNull @Positive BigDecimal valor, @NotNull @Positive Long quantidade, @Length(max=1000) String descricao,
                       @NotNull Long idCategoria,
                        List<NovaCaracteristicaForm> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.caracteristicas.addAll(caracteristicas);

    }
    public ProdutoForm() {
    }

    public List<NovaCaracteristicaForm> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
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

    public Long getIdCategoria() {
        return idCategoria;
    }



    public Produto converter(EntityManager manager, Usuario usuarioAuthCadastroProduto) {
        @NotNull @NotBlank Categoria categoria = manager.find(Categoria.class, idCategoria);
        return new Produto(nome, valor, quantidade, descricao, categoria, usuarioAuthCadastroProduto, caracteristicas);
    }

    public Set<String> buscaCaractristicasIguais(){
        HashSet<String> nomesIguais = new HashSet<>();
        HashSet<String> resultado = new HashSet<>();

        for(NovaCaracteristicaForm caracteristica : caracteristicas){ String nome = caracteristica.getNome();
            if(!nomesIguais.add(nome)){
                resultado.add(nome);
            }

        }
        return resultado;
    }

}
