package br.com.zupacademy.ane.mercadolivre.mercadolivre.validacao;

public class ErroFormDto {
    private String nome;
    private String erro;


    public ErroFormDto(String nome, String erro) {
        this.nome = nome;
        this.erro = erro;
    }

    public String getNome() {
        return nome;
    }

    public String getErro() {
        return erro;
    }
}
