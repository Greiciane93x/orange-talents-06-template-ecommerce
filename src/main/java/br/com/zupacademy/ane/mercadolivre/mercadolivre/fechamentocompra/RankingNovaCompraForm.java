package br.com.zupacademy.ane.mercadolivre.mercadolivre.fechamentocompra;

import javax.validation.constraints.NotNull;


public class RankingNovaCompraForm {

    @NotNull
    private Long idCompra;

    @NotNull
    private Long idDonoProduto;

    public RankingNovaCompraForm(Long idCompra, Long idDonoProduto) {
        this.idCompra = idCompra;
        this.idDonoProduto = idDonoProduto;
    }

    @Override
    public String toString() {
        return "RankingNovaCompraForm{" +
                "idCompra=" + idCompra +
                ", idDonoProduto=" + idDonoProduto +
                '}';
    }

}

