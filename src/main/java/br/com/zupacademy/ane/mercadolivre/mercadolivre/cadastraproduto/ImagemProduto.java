package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastraproduto;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @NotNull @Valid
    private Produto produto;

    @URL @NotBlank
    private String link;

    @Deprecated
    public ImagemProduto() {
    }

    public ImagemProduto(@NotNull @Valid Produto produto,
                         @URL @NotBlank String link) {
        this.produto = produto;
        this.link = link;
    }


    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public Produto getProduto() {
        return produto;
    }


    @Override
    public String toString() {
        return "ImagemProduto{" +
                "id=" + id +
                ", link='" + link + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImagemProduto that = (ImagemProduto) o;
        return id.equals(that.id) && produto.equals(that.produto) && link.equals(that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, produto, link);
    }
}
