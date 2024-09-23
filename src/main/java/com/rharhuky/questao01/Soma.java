package com.rharhuky.questao01;

public class Soma {

    public static void main(String[] args) {
        int indice = 13, soma = 0, k = 0;

        while( k < indice) {
            k = k + 1;
            soma = soma + k;
        }
        System.out.printf("Valor da variável soma: %d", soma); // 91
    }
}
