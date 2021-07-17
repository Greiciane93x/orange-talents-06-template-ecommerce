package br.com.zupacademy.ane.mercadolivre.mercadolivre.fechamentocompra;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastraproduto.Produto;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.Usuario;
import io.jsonwebtoken.lang.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @NotNull @Valid
    private Produto produtoEscolhido;

    @Positive
    private int quantidade;

    @ManyToOne @NotNull @Valid
    private Usuario comprador;

    @Enumerated(EnumType.STRING) @NotNull
    private GatewayPagamento gateway;

    @NotNull
    private UUID uuid;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

    private String statusCompra;

    public Compra(@NotNull @Valid Produto produtoASerComprado,
                  @Positive int quantidade,
                  @NotNull @Valid Usuario comprador, GatewayPagamento gateway,
                  @NotBlank String statusCompra
                 ) {
        this.uuid = UUID.randomUUID();
        this.produtoEscolhido = produtoASerComprado;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.gateway = gateway;
        this.statusCompra = "INICIADO";
    }

    @Deprecated
    public Compra() {
    }

    // necessário p/ gerar nota fiscal
    public Long getId() {
        return id;
    }

    public String urlRedirecionamento(GatewayPagamento novaCompra, UriComponentsBuilder uriComponentsBuilder){
        return this.gateway.criaUrlRetorno(this, uriComponentsBuilder);
    }

    public void adicionaTransacao(@Valid RetornoGatewayPagamento form){
        Transacao novaTransacao = form.toTransacao(this);

        Assert.state(!this.transacoes.contains(novaTransacao),
                "Já existe uma transacao processada igual a essa");

        Assert.state(transacoesConcluidasComSucesso().isEmpty(), "Essa compra foi concluída com sucesso");

        this.transacoes.add(novaTransacao);
    }


    public void tentativaPagamento(RetornoGatewayPagamento form) {
        Transacao novaTransacao = form.toTransacao(this);
        Assert.isTrue(!this.transacoes.contains(novaTransacao), "Já existe transação...");
        Set<Transacao> transacaoConcluidaComSucesso = this.transacoes.stream().
                filter(Transacao :: concluidaComSucesso).collect(Collectors.toSet());

        Assert.isTrue(transacaoConcluidaComSucesso.isEmpty(), "Essa compra já foi concluída com sucesso");
        this.transacoes.add(novaTransacao);
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", produtoEscolhido=" + produtoEscolhido +
                ", quantidade=" + quantidade +
                ", comprador=" + comprador +
                ", gateway=" + gateway +
                ", transacoes=" + transacoes +
                '}';
    }

    public Usuario getComprador() {
        return comprador;
    }

    public Set<Transacao> transacoesConcluidasComSucesso() {
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                .filter(Transacao::concluidaComSucesso)
                .collect(Collectors.toSet());
        Assert.isTrue(transacoesConcluidasComSucesso.size() <= 1, "Tem mais de uma transação concluída na compra.. "
        + this.id);
        return transacoesConcluidasComSucesso;
    }

    public boolean processadaComSucesso(){
        return !transacoesConcluidasComSucesso().isEmpty();
    }


}
