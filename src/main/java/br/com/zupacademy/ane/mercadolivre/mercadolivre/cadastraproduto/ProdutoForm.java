package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastraproduto;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EntityManager;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class ProdutoForm {

    @NotNull @NotBlank
    private String nome;
    @NotNull
    private BigDecimal valor;
    @NotNull
    private Long quantidade;

    @NotNull
    @JsonProperty("idCaracteristica")
    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    private Long idCaracteristica;
    @NotNull
    private Long idCategoriaProduto;
    @JsonProperty("caracteristicas")
    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    private @NotNull @Min(3) Long caracteristicas;

    public ProdutoForm() {
    }

    public ProdutoForm(@NotBlank @NotNull String nome,
                       @NotNull BigDecimal valor,
                       @NotNull  Long quantidade,
                       @NotNull  LocalDateTime instante,
                       @NotNull  Long idCaracteristica,
                       @NotNull Long idCategoriaProduto,
                       @JsonProperty("caracteristicas")
                       Set<Caracteristica> caracteristicas) {
        this.nome = nome;
        if(valor.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException();
        }
        this.valor = valor;
        if(quantidade <=0){
            throw new IllegalArgumentException();
        }
        this.quantidade = quantidade;

        this.caracteristicas = idCaracteristica;

        this.idCategoriaProduto = idCategoriaProduto;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Long getCaracteristicas() {
        return caracteristicas;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public Long getIdCaracteristica() {
        return idCaracteristica;
    }

    public Long getIdCategoriaProduto() {
        return idCategoriaProduto;
    }

    public Produto converter(EntityManager manager, Usuario usuarioAuthCadastroProduto) {
        @NotNull @NotBlank  CategoriaProduto categoria = manager.find(CategoriaProduto.class, idCategoriaProduto);
        @NotNull @NotBlank Set<Caracteristica> caracteristica = (Set<Caracteristica>) manager.find(Caracteristica.class, idCaracteristica);

        return new Produto(nome, valor, quantidade, caracteristica,categoria);
    }
}
