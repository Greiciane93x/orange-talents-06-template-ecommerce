package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastraproduto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaCaracteristicaForm {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @Deprecated
    public NovaCaracteristicaForm() {
    }

    public NovaCaracteristicaForm(@NotBlank String nome,
                                  @NotBlank  String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public CaracteristicaProduto converter(@NotNull @Valid Produto produto) {
        return new CaracteristicaProduto(nome, descricao, produto);
    }
}
