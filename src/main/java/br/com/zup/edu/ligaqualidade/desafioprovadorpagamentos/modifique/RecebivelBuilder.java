package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosTransacao;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.MetodoPagamento;

import java.util.ArrayList;
import java.util.List;

public class RecebivelBuilder {

    public List<Recebivel> buildRecebiveis(List<DadosTransacao> transacoes) {
        List<Recebivel> recebiveis = new ArrayList<>();

        for (DadosTransacao transacao : transacoes) {
            if (MetodoPagamento.DEBITO.equals(transacao.metodo)) {
                recebiveis.add(new Debito(transacao));
            } else {
                recebiveis.add(new Credito(transacao));
            }
        }

        return recebiveis;
    }
}
