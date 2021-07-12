package br.com.zupacademy.ane.mercadolivre.mercadolivre.fechamentocompra;

import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {

    PAGSEGURO {
        @Override
        String criaUrlRetorno(Compra compra,
                              UriComponentsBuilder uriComponentsBuilder) {
            String urlRetornoPagseguro = uriComponentsBuilder
                    .path("/retorno-pagseguro/{id}")
                    .buildAndExpand(compra.getId()).toString();

            return "pagseguro.com?returnId" + compra.getId() + "&redirectUrl="
                    + urlRetornoPagseguro;
        }
    },
    PAYPAL {

        @Override
        String criaUrlRetorno(Compra compra,
                              UriComponentsBuilder uriComponentsBuilder) {
            String urlRetornoPaypal = uriComponentsBuilder
                    .path("/retorno-paypal/{id}").buildAndExpand(compra.getId())
                    .toString();

            return "paypal.com?buyerdId=" + compra.getId() + "&redirectUrl=" + urlRetornoPaypal;
        }

    };
    abstract String criaUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder);
}
