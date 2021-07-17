package br.com.zupacademy.ane.mercadolivre.mercadolivre.fechamentocompra;
import br.com.zupacademy.ane.mercadolivre.mercadolivre.adicionapergunta.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.BindException;

@RestController
public class ProcessamentoCompraController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private Mailer email;

    @PostMapping(value="/retorno-pagseguro/{id}")
    @Transactional
    public ResponseEntity<RetornoGatewayPagamentoDto> processamentoPagSeguro(@PathVariable("id") Long idCompra,
                                                                             @Valid @RequestBody RetornoPagSeguroForm form,
                                                                             BindingResult bindingResult) throws BindException {
        if(bindingResult.hasErrors()){
            throw new BindException();
        }
        Compra compra = manager.find(Compra.class, idCompra);
        StatusRetornoPagseguro statusCompra = form.getStatus();
        compra.tentativaPagamento(form);

        manager.merge(compra);
        System.out.println(email.send(
                "Sua transação foi realizada com sucesso!",
                "Transação",
                "@testemail"
                ,"teste@email", "test@email"
        ));
        return ResponseEntity.status(200).body(new RetornoGatewayPagamentoDto(statusCompra,compra,GatewayPagamento.PAGSEGURO));
    }

    @PostMapping(value="/retorno-paypal/{id}")
    @Transactional
    public ResponseEntity<RetornoGatewayPagamentoDto> processamentoPayPal(@PathVariable("id") Long idCompra,
                                         @Valid @RequestBody RetornoPayPalForm form, BindingResult bindingResult ) throws BindException {

        if(bindingResult.hasErrors()){
            throw new BindException();
        }
        Compra compra = manager.find(Compra.class, idCompra);
        compra.tentativaPagamento(form);

        manager.merge(compra);
        System.out.println(email.send(
                "Sua transação foi realizada com sucesso!",
                "Transação",
                "@testemail"
                ,"teste@email", "test@email"
        ));
        return ResponseEntity.status(200).body(new RetornoGatewayPagamentoDto(new RetornoPayPalForm().getStatus(),compra,GatewayPagamento.PAYPAL));
    }

}
