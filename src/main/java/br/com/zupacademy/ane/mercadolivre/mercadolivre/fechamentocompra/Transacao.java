package br.com.zupacademy.ane.mercadolivre.mercadolivre.fechamentocompra;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Para ficar claro de que se trata de uma
     * transação é o id provido pelo gateway
     */

    @NotNull
    private StatusTransacao status;

    @NotNull
    private Long idTransacaoGateway;

    @NotNull
    private LocalDate instante;

    @ManyToOne
    private Compra compra;

    public Transacao(@NotNull StatusTransacao status,
                     @NotNull Long idTransacaoGateway,
                     Compra compra) {
        this.status = status;
        this.instante = LocalDate.now();
        this.idTransacaoGateway = idTransacaoGateway;
        this.compra = compra;
    }

    @Deprecated
    public Transacao() {
    }

    public Integer getId() {
        return id;
    }

    public StatusTransacao getStatus() {
        return status;
    }

    public Long getIdTransacaoGateway() {
        return idTransacaoGateway;
    }

    public LocalDate getInstante() {
        return instante;
    }

    public Compra getCompra() {
        return compra;
    }

    public boolean concluidaComSucesso(){
        return this.status.equals(StatusTransacao.sucesso);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return id.equals(transacao.id) && status == transacao.status && idTransacaoGateway.equals(transacao.idTransacaoGateway) && compra.equals(transacao.compra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, idTransacaoGateway, compra);
    }
}
