package br.com.zupacademy.ane.mercadolivre.mercadolivre.fechamentocompra;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class RetornoPayPalForm implements RetornoGatewayPagamento{
    @Min(0)
    @Max(1)
    private int status;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long idTransacao;

    public RetornoPayPalForm(int status) {
        this.status = status;
        this.idTransacao = idTransacao;
    }

    @Deprecated
    public RetornoPayPalForm() {
    }

    public int getStatus() {
        return status;
    }

    public Long getIdTransacao() {
        return idTransacao;
    }

    public Transacao toTransacao(Compra compra){
        return new Transacao(this.status == 0 ? StatusTransacao.erro:StatusTransacao.sucesso,
                idTransacao, compra);
    }
}
