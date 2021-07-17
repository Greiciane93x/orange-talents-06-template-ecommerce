package br.com.zupacademy.ane.mercadolivre.mercadolivre.fechamentocompra;

public enum StatusRetornoPagseguro {

    SUCESSO, ERRO;

    public StatusTransacao normaliza(){

        if(this.equals(SUCESSO)){
            return StatusTransacao.sucesso;
        }
        return StatusTransacao.erro;
    }
}
