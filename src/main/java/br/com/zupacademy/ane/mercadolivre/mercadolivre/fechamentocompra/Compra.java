package br.com.zupacademy.ane.mercadolivre.mercadolivre.fechamentocompra;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastraproduto.Produto;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrousuario.Usuario;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.UUID;

@Entity
public class Compra {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne @NotNull @Valid
    private Produto produtoEscolhido;

    @Positive
    private int quantidade;

    @ManyToOne @NotNull @Valid
    private Usuario comprador;

    @Enumerated(EnumType.STRING) @NotNull
    private GatewayPagamento gateway;

    @NotBlank
    private String statusCompra = "INICIADO";

    public Compra(@NotNull @Valid Produto produtoASerComprado,
                  @Positive int quantidade,
                  @NotNull @Valid Usuario comprador, GatewayPagamento gateway,
                  @NotBlank String statusCompra
                 ) {
        this.produtoEscolhido = produtoASerComprado;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.gateway = gateway;
        this.statusCompra = statusCompra;
    }

    @Deprecated
    public Compra() {
    }

    public String urlRedirecionamento(GatewayPagamento novaCompra, UriComponentsBuilder uriComponentsBuilder){
        return this.gateway.criaUrlRetorno(this, uriComponentsBuilder);
    }

    public UUID getId() {
        return id;
    }
}
