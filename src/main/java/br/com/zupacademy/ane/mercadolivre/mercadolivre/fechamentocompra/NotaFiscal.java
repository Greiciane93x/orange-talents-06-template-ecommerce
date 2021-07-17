package br.com.zupacademy.ane.mercadolivre.mercadolivre.fechamentocompra;

import io.jsonwebtoken.lang.Assert;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotaFiscal implements EventoCompraSucesso{

    public void processa(Compra compra){

        //Esse Assert.isTrue nos remete a programação defensiva
        Assert.isTrue(compra.processadaComSucesso(), "Compra não pode ser concluída com sucesso");

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idComprador", compra.getComprador());

        restTemplate.postForEntity("http://localhost:8080/notas-fiscais", request, String.class);

    }

}
