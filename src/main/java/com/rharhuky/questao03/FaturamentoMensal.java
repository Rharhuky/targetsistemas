package com.rharhuky.questao03;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import static com.rharhuky.utils.JsonUtil.objectMapper;
import static java.util.Comparator.comparingDouble;

public class FaturamentoMensal {

    private final String dadosFaturamentoPath;

    private static final double ZERO = 0.0;

    public FaturamentoMensal(String dadosFaturamentoPath) {
        this.dadosFaturamentoPath = dadosFaturamentoPath;
    }

    public static List<FaturamentoDiario> obterFaturamento(String dadosFaturamentoPath) {
        List<FaturamentoDiario> faturamentoMensal = null;
        try {
            faturamentoMensal = objectMapper().readValue(new File(dadosFaturamentoPath), new TypeReference<>(){});
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo de dados !");
            System.err.println(e.getMessage());
        }
        return faturamentoMensal;
    }

    public Optional<FaturamentoDiario> menorValor(List<FaturamentoDiario> faturamentoDiario){
        return faturamentoDiario
                .stream()
                .filter(this::semFaturamento)
                .min(comparingDouble(FaturamentoDiario::getValor));
    }
    public Optional<FaturamentoDiario> maiorValor(List<FaturamentoDiario> faturamentoDiario){
        return faturamentoDiario
                .stream()
                .filter(this::semFaturamento)
                .max(comparingDouble(FaturamentoDiario::getValor));
    }

    public int diasAcimaMediaMensal(List<FaturamentoDiario> faturamentoDiarios){
        OptionalDouble media = obterMediaMensal(faturamentoDiarios);
        if(media.isPresent()){
            var mediaMensal = media.getAsDouble();
            return (int) faturamentoDiarios
                    .stream()
                    .filter(fatDiario -> semFaturamento(fatDiario)  && fatDiario.getValor() > mediaMensal)
                    .count();
        }
        return 0;
    }

    private boolean semFaturamento(FaturamentoDiario faturamentoDiario){
        return faturamentoDiario.getValor() > ZERO;
    }

    public static OptionalDouble obterMediaMensal(List<FaturamentoDiario> faturamentoDiario){
        return faturamentoDiario
                .stream()
                .mapToDouble(FaturamentoDiario::getValor)
                .average();
    }

    public void exibirRelatorioMensal(){
        List<FaturamentoDiario> faturamentosDiarios = obterFaturamento(dadosFaturamentoPath);

        menorValor(faturamentosDiarios).ifPresent(faturamentoDiario -> {
            System.out.printf("Menor valor de faturamento mensal | Dia: %-2d | Valor : %-3f%n", faturamentoDiario.getDia(), faturamentoDiario.getValor());
        });

        maiorValor(faturamentosDiarios).ifPresent(faturamentoDiario -> {
            System.out.printf("Maior valor de faturamento mensal | Dia: %-2d | Valor : %-3f%n", faturamentoDiario.getDia(), faturamentoDiario.getValor());
        });

        System.out.println("Número de dias acima da média mensal: " + diasAcimaMediaMensal(faturamentosDiarios));
    }

    public static void main(String[] args) {
        new FaturamentoMensal("src/main/resources/dados.json")
                .exibirRelatorioMensal();
    }
}
