package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.recebivel;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosRecebimentoAdiantado;

import java.util.List;
import java.util.stream.Collectors;

public class RecebivelHandler {

    public List<String[]> processRecebiveis(List<Recebivel> recebiveis,
                                            List<DadosRecebimentoAdiantado> adiantamentos) {
        return recebiveis.stream()
                .map(Recebivel::processRecebivel)
                .collect(Collectors.toList());
    }
}
