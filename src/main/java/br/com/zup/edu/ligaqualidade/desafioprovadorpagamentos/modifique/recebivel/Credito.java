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
    private static final int INDEX_STATUS = 0;
    private static final int INDEX_TRANSACAO_VALOR = 1;
    private static final int INDEX_VALOR_APOS_TAXA = 2;
    private static final int INDEX_DATA_RECEBIMENTO = 3;
    private static final int INDEX_ID_RECEBIVEL = 4;

    public final DadosTransacao transacao;

    public Credito(DadosTransacao transacao) {
        this.transacao = transacao;
    }

    @Override
    public String[] processRecebivel() {
        String[] recebivel = new String[5];

        recebivel[INDEX_STATUS] = STATUS;
        recebivel[INDEX_TRANSACAO_VALOR] = transacao.valor.toString();
        recebivel[INDEX_VALOR_APOS_TAXA] = calculateDiscount().toString();
        recebivel[INDEX_DATA_RECEBIMENTO] = LocalDate.now().plusDays(DAYS).format(dateFormatter);
        recebivel[INDEX_ID_RECEBIVEL] = String.valueOf(transacao.id);

        return recebivel;
    }

    private BigDecimal calculateDiscount() {
        return transacao.valor.subtract(transacao.valor.multiply(DISCOUNT))
                .setScale(0, RoundingMode.HALF_EVEN);
    }
}
