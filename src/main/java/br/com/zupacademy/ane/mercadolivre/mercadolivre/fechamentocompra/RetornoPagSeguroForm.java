package br.com.zupacademy.ane.mercadolivre.mercadolivre.fechamentocompra;
import javax.validation.constraints.NotNull;
import java.util.UUID;


public class RetornoPagSeguroForm implements  RetornoGatewayPagamento{

    private Long idTransacao;

    @NotNull
    private StatusRetornoPagseguro status;

    public RetornoPagSeguroForm(StatusRetornoPagseguro status) {
        this.status = status;
        this.idTransacao = idTransacao;
    }
    @Deprecated
    public RetornoPagSeguroForm() {
    }

    @Override
    public String toString() {
        return "RetornoPagSeguroForm{" +
                "idTransacao='" + idTransacao + '\'' +
                ", status=" + status +
                '}';
    }

    public Long getIdTransacao() {
        return idTransacao;
    }

    public StatusRetornoPagseguro getStatus() {
        return status;
    }

    public Transacao toTransacao(Compra compra) {
        return new Transacao(status.normaliza(), idTransacao, compra);
    }

}

