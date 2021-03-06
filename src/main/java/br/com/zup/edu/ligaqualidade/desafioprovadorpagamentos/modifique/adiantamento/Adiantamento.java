package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.adiantamento;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosRecebimentoAdiantado;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class Adiantamento {

    private static final String STATUS = "pago";
    private static final int DAYS = 30;
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final int INDEX_STATUS = 0;
    private static final int INDEX_VALOR_APOS_TAXA = 2;
    private static final int INDEX_DATA_RECEBIMENTO = 3;
    private static final int INDEX_ID_RECEBIVEL = 4;

    public static String[] processAdiantamento(String[] recebivel, BigDecimal taxa) {
        BigDecimal valorAntigo = new BigDecimal(recebivel[INDEX_VALOR_APOS_TAXA]);

        recebivel[INDEX_STATUS] = STATUS;
        recebivel[INDEX_VALOR_APOS_TAXA] = calculateDiscount(taxa, valorAntigo).toString();
        recebivel[INDEX_DATA_RECEBIMENTO] = LocalDate.parse(recebivel[INDEX_DATA_RECEBIMENTO], dateFormatter)
                .minusDays(DAYS).format(dateFormatter);

        return recebivel;
    }

    private static BigDecimal calculateDiscount(BigDecimal taxa, BigDecimal valorAntigo) {
        return valorAntigo.subtract(valorAntigo.multiply(taxa))
                .setScale(0, RoundingMode.HALF_EVEN);
    }

    public static String[] applyAdiantamentoIfExists(String[] recebivel, List<DadosRecebimentoAdiantado> dadosRecebimentoAdiantados) {
        Optional<DadosRecebimentoAdiantado> dadoRecebimentoAdiantado = dadosRecebimentoAdiantados
                .stream()
                .parallel().filter(adiantado -> String.valueOf(adiantado.idTransacao).equals(recebivel[INDEX_ID_RECEBIVEL]))
                .findAny();

        return dadoRecebimentoAdiantado.map(adiantado -> {
           return processAdiantamento(recebivel, adiantado.taxa);
        }).orElse(recebivel);
    }

}
