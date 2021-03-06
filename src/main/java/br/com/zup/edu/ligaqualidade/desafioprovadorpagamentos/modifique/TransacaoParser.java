package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosRecebimentoAdiantado;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosTransacao;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.MetodoPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class TransacaoParser {

    private static final String SPLIT_CHAR = ",";
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final int INDEX_TRANSACAO_VALOR = 0;
    private static final int INDEX_TRANSACAO_METODO_PAGAMENTO = 1;
    private static final int INDEX_TRANSACAO_NUMERO_CARTAO = 2;
    private static final int INDEX_TRANSACAO_NOME_CARTAO = 3;
    private static final int INDEX_TRANSACAO_VALIDADE = 4;
    private static final int INDEX_TRANSACAO_CVV = 5;
    private static final int INDEX_TRANSACAO_ID_TRANSACAO = 6;

    private static final int INDEX_ADIANTAMENTO_ID_TRANSACAO = 0;
    private static final int INDEX_ADIANTAMENTO_TAXA = 1;

    public List<DadosTransacao> parseTransactions(List<String> infoTransacoes) {
        return infoTransacoes.stream().
                map(transacao -> parseTransaction(transacao))
                .collect(Collectors.toList());
    }

    private DadosTransacao parseTransaction(String transacao) {
        String dados[] = transacao.split(SPLIT_CHAR);

        return new DadosTransacao(
                new BigDecimal(dados[INDEX_TRANSACAO_VALOR]),
                MetodoPagamento.valueOf(dados[INDEX_TRANSACAO_METODO_PAGAMENTO]),
                dados[INDEX_TRANSACAO_NUMERO_CARTAO],
                dados[INDEX_TRANSACAO_NOME_CARTAO],
                LocalDate.parse(dados[INDEX_TRANSACAO_VALIDADE], dateFormatter),
                Integer.parseInt(dados[INDEX_TRANSACAO_CVV]),
                Integer.parseInt(dados[INDEX_TRANSACAO_ID_TRANSACAO])
        );
    }
}
