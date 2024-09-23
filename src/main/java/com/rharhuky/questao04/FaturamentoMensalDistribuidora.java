package com.rharhuky.questao04;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.text.NumberFormat.getCurrencyInstance;

public class FaturamentoMensalDistribuidora {

    private final Map<String, Double> faturamentoMensal;

    public FaturamentoMensalDistribuidora(Map<String, Double> faturamentoMensal) {
        this.faturamentoMensal = faturamentoMensal;
    }

    public void obterRelatorio(){
        var total = obterValorTotal();
        total.ifPresent(valorTotal -> this.faturamentoMensal
                .forEach((estado, valor) ->
                        exibirRepresentacaoEstado(estado, valor, ((valor / valorTotal) * 100))
                ));
    }

    private void exibirRepresentacaoEstado(String estado, double valor, double valorRepresentacao){
        System.out.printf("%-10s : %-5s -> %.2f%%\n", estado, formatarValorMonetario(valor), valorRepresentacao);
    }

    private String formatarValorMonetario(double valor){
        return getCurrencyInstance().format(valor);
    }

    private Optional<Double> obterValorTotal(){
        return this.faturamentoMensal
                .values()
                .stream()
                .reduce(Double::sum);
    }

    public static void main(String[] args) {
        Map<String, Double> dados = new HashMap<>(){{
            put("SP", 67836.43);
            put("RJ", 36678.66);
            put("MG", 29229.88);
            put("ES", 27165.48);
            put("Outros", 19849.53);
        }};
        FaturamentoMensalDistribuidora faturamento = new FaturamentoMensalDistribuidora(dados);
        faturamento.obterRelatorio();
    }
}
