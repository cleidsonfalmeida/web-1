package com.exercicio.conversao.model;

import org.springframework.context.MessageSource;
import java.util.Locale;

public class Conversao {
    private String opcao;
    private Double valor;

    public String getOpcao() { return opcao; }
    public void setOpcao(String opcao) { this.opcao = opcao; }
    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public String calcularResultado(MessageSource messageSource, Locale locale) {
        if (opcao == null || valor == null) {
            throw new IllegalArgumentException("Dados inválidos.");
        }

        double resultado;
        String chave;

        switch (opcao) {
            case "mi-m": resultado = valor * 1609.34; chave = "mensagem.resultado.mi.m"; break;
            case "m-mi": resultado = valor / 1609.34; chave = "mensagem.resultado.m.mi"; break;
            case "ft-m": resultado = valor * 0.3048; chave = "mensagem.resultado.ft.m"; break;
            case "m-ft": resultado = valor / 0.3048; chave = "mensagem.resultado.m.ft"; break;
            default: throw new IllegalArgumentException("Opção inválida.");
        }

        return String.format(messageSource.getMessage(chave, null, locale), valor, resultado);
    }
}
