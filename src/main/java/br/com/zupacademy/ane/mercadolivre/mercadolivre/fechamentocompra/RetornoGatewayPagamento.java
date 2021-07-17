package br.com.zupacademy.ane.mercadolivre.mercadolivre.fechamentocompra;

public interface RetornoGatewayPagamento {

    Transacao toTransacao(Compra compra);

}
