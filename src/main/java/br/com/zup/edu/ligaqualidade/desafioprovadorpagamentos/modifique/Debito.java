package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosTransacao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Debito implements Recebivel {

    private static final String STATUS = "pago";
    private static final BigDecimal DISCOUNT = new BigDecimal(0.03);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public final DadosTransacao transacao;

    public Debito(DadosTransacao transacao){
        this.transacao = transacao;
    }

    @Override
    public String[] processRecebivel() {
        String[] recebivel = new String[4];

        recebivel[0] = STATUS;
        recebivel[1] = transacao.valor.toString();
        recebivel[2] = calculateDiscount().toString();
        recebivel[3] = LocalDate.now().format(dateFormatter);

        return recebivel;
    }

    private BigDecimal calculateDiscount() {
        return transacao.valor.subtract(transacao.valor.multiply(DISCOUNT))
                .setScale(0, RoundingMode.HALF_EVEN);
    }
}
