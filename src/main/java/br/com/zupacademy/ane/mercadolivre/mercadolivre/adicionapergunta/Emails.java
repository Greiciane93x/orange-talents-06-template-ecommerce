package br.com.zupacademy.ane.mercadolivre.mercadolivre.adicionapergunta;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.fechamentocompra.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
public class Emails{
    @Autowired
    private Mailer mailer;

    public void novaPergunta(@NotNull @Valid  Pergunta pergunta) {

        mailer.send("<html>Isso é um envio de Email...</html>","Nova Pergunta: >>>>>>>>>>", pergunta.getQuemPerguntou().getLogin(), "novapergunta@nossomercadolivre.com",pergunta.getDonoProduto().getLogin());

    }

}
