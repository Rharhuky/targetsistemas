package com.rharhuky.questao05;

import java.util.Scanner;

import static java.lang.System.in;

public class Inversor {

    private static final StringBuilder STRING_INVERSA = new StringBuilder();

    public static String inverter(String string) {
        STRING_INVERSA.setLength(0);
        char[] caracteres = string.toCharArray();
        for (int posicao = caracteres.length -1 ; posicao >= 0; posicao --) {
            STRING_INVERSA.append(caracteres[posicao]);
        }
        return STRING_INVERSA.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        String input = "";
        System.out.println(" --- Para encerrar, nao insira e tecle enter ---");
        do {
            System.out.print("Insira algo: ");
            input = scanner.nextLine();
            System.out.println( inverter(input));
        } while(! input.trim().isBlank());
    }
}
