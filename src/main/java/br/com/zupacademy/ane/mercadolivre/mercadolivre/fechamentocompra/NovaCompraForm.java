package br.com.zupacademy.ane.mercadolivre.mercadolivre.fechamentocompra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaCompraForm {

    @Positive
    private  int quantidade;

    @NotNull
    private Long idProduto;

    @NotNull
    private GatewayPagamento gateway;

    @NotBlank
    private String statusCompra;

    public NovaCompraForm(@Positive  int quantidade,
                          @NotNull  Long idProduto,
                          GatewayPagamento gateway,
                          String statusCompra
                          ) {
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        this.gateway = gateway;
        this.statusCompra = "EM ANDAMENTO";
    }

    public String getStatusCompra() {
        return statusCompra;
    }
    public Long getIdProduto() {
        return  idProduto;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public GatewayPagamento getGateway() {
        return gateway;
    }
}

