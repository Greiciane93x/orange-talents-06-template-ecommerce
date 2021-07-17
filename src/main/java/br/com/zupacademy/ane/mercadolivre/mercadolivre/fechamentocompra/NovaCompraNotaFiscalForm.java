package br.com.zupacademy.ane.mercadolivre.mercadolivre.fechamentocompra;
import javax.validation.constraints.NotNull;

public class NovaCompraNotaFiscalForm {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idComprador;

    public NovaCompraNotaFiscalForm(Long idCompra, Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdComprador() {
        return idComprador;
    }

    @Override
    public String toString() {
        return "NovaCompraNotaFiscalForm{" +
                "idCompra=" + idCompra +
                ", idComprador=" + idComprador +
                '}';
    }
}

