package br.com.zupacademy.ane.mercadolivre.mercadolivre.cadastraproduto;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class NovasImgsForm {

    @Size(min = 1)
    @NotNull
    private List<MultipartFile> img = new ArrayList<>();

    //pelo que eu entendi, por não ser um json, muda o desserializador
    //por isso a necessidade de um set
    public void setImg(List<MultipartFile> img) {
        this.img = img;
    }
    public List<MultipartFile> getImgs() {
        return img;
    }


}
