package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.recebivel;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosTransacao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Credito implements Recebivel {

    private static final String STATUS = "aguardando_pagamento";
    private static final BigDecimal DISCOUNT = new BigDecimal(0.05);
    private static final int DAYS = 30;
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public final DadosTransacao transacao;

    public Credito(DadosTransacao transacao) {
        this.transacao = transacao;
    }

    @Override
    public String[] processRecebivel() {
        String[] recebivel = new String[4];

        recebivel[0] = STATUS;
        recebivel[1] = transacao.valor.toString();
        recebivel[2] = calculateDiscount().toString();
        recebivel[3] = LocalDate.now().plusDays(DAYS).format(dateFormatter);

        return recebivel;
    }

    private BigDecimal calculateDiscount() {
        return transacao.valor.subtract(transacao.valor.multiply(DISCOUNT))
                .setScale(0, RoundingMode.HALF_EVEN);
    }
}
