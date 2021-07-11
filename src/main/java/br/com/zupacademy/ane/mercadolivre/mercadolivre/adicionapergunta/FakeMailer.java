package br.com.zupacademy.ane.mercadolivre.mercadolivre.adicionapergunta;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Component
@Primary
public class FakeMailer implements Mailer{

    /**
     * @param corpoEmail   - Corpo do Email
     * @param assuntoEmail - Assunto do Email
     * @param nameFrom     - Nome para aparecer no provedor de Email
     * @param from         - Email de Origem
     * @param to           - Email de Destino
     */
    @Override
    public void send(@NotBlank String corpoEmail, @NotBlank String assuntoEmail,
                     @NotBlank String nameFrom, @NotBlank @Email String from,
                     @NotBlank @Email String to) {

        System.out.println(corpoEmail);
        System.out.println(assuntoEmail);
        System.out.println(nameFrom);
        System.out.println(from);
        System.out.println(to);
    }
}
