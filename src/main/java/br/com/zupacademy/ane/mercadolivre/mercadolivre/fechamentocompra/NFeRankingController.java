package br.com.zupacademy.ane.mercadolivre.mercadolivre.fechamentocompra;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class NFeRankingController {

    // São criadas duas tarefas com seu próprio passo.
    // Nesse caso é para simular
    @PostMapping(value ="/notas-fiscais")
    public void criaNotaFiscal(@RequestBody @Valid NovaCompraNotaFiscalForm form) throws InterruptedException {
        System.out.println("Criando nota fiscal..: " + form);
        Thread.sleep(150);
    }

    @PostMapping(value = "/ranking")
    public void ranking(@RequestBody @Valid RankingNovaCompraForm form ) throws  InterruptedException{
        System.out.println("Criando ranking..: " + form);
        Thread.sleep(150);
    }
}
