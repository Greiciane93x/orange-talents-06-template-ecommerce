package br.com.zupacademy.ane.mercadolivre.mercadolivre.adicionaopiniao;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.adicionaopiniao.Opiniao;

public class OpiniaoDto {

    private int nota;
    private String titulo;
    private String descricao;

    public OpiniaoDto(Opiniao opiniao) {
        this.nota = opiniao.getNota();
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
    }

    public int getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
