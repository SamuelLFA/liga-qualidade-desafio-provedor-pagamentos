package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosRecebimentoAdiantado;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosTransacao;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.MetodoPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class AdiantamentoParser {

    private static final String SPLIT_CHAR = ",";
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final int INDEX_ADIANTAMENTO_ID_TRANSACAO = 0;
    private static final int INDEX_ADIANTAMENTO_TAXA = 1;

    public List<DadosRecebimentoAdiantado> parseAdiantamentos(List<String> infoAdiantamentos) {
        return infoAdiantamentos.stream()
                .map(adiantamento -> parseAdiantamento(adiantamento))
                .collect(Collectors.toList());
    }

    private DadosRecebimentoAdiantado parseAdiantamento(String adiantamento) {
        String dados[] = adiantamento.split(SPLIT_CHAR);
        return new DadosRecebimentoAdiantado(
                Integer.parseInt(dados[INDEX_ADIANTAMENTO_ID_TRANSACAO]),
                new BigDecimal(dados[INDEX_ADIANTAMENTO_TAXA])
        );
    }
}
