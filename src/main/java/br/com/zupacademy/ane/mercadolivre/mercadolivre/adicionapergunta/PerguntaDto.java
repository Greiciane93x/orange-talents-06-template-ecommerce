package br.com.zupacademy.ane.mercadolivre.mercadolivre.adicionapergunta;

public class PerguntaDto {

    private String titulo;

    public PerguntaDto(Pergunta pergunta){
        this.titulo = pergunta.getTitulo();

    }

    public String getTitulo() {
        return titulo;
    }
}
