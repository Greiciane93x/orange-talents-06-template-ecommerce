package br.com.zupacademy.ane.mercadolivre.mercadolivre.fechamentocompra;

public class NovaCompraDto {
    private String urlRedirecionamento;
    private String statusCompra;

    public NovaCompraDto(String urlRedirecionamento) {
        this.urlRedirecionamento = urlRedirecionamento;
        this.statusCompra = "FINALIZADO";
    }

    public String getUrlRedirecionamento() {
        return urlRedirecionamento;
    }

    public String getStatusCompra() {
        return statusCompra;
    }
}
