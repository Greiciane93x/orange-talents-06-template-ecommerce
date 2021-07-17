package br.com.zupacademy.ane.mercadolivre.mercadolivre.fechamentocompra;

public class RetornoGatewayPagamentoDto {

    private Long idCompra;
    private StatusRetornoPagseguro statusCompra;
    private int status;
    private GatewayPagamento gatewayPagamento;


    public RetornoGatewayPagamentoDto(StatusRetornoPagseguro statusCompra, Compra compra, GatewayPagamento pagseguro) {
        this.statusCompra = statusCompra;
        this.idCompra = compra.getId();
        this.gatewayPagamento = pagseguro;

    }

    public RetornoGatewayPagamentoDto(int status, Compra compra, GatewayPagamento paypal) {
        this.status = status;
        this.idCompra =  getIdCompra();
        this.gatewayPagamento = paypal;
    }


    public Long getIdCompra() {
        return idCompra;
    }

    public StatusRetornoPagseguro getStatusCompra() {
        return statusCompra;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }

    public int getStatus() {
        return status;
    }
}
