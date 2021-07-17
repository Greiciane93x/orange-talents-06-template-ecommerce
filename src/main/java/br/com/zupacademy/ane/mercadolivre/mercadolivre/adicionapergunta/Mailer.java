package br.com.zupacademy.ane.mercadolivre.mercadolivre.adicionapergunta;

import org.springframework.stereotype.Component;

@Component
public interface Mailer {
    /**
     *  @param corpoEmail - Corpo do Email
     * @param assuntoEmail - Assunto do Email
     * @param nameFrom - Nome para aparecer no provedor de Email
     * @param from - Email de Origem
     * @param to - Email de Destino
     * @return
     */

    String send(String corpoEmail, String assuntoEmail, String nameFrom, String from , String to);

}
