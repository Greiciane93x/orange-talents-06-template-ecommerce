package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastrocategoria;

public class CategoriaDto {

    private String nome;

    public CategoriaDto(Categoria categoria) {
        this.nome = categoria.getNome();
    }
}
