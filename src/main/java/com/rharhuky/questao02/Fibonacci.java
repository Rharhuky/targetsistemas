package com.rharhuky.questao02;

import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.System.in;

public class Fibonacci {

    public static String fibonacci(int n) {
        int anterior = 0;
        int posterior = 1;
        while (posterior < n) {
            int atual = anterior + posterior;
            anterior = posterior;
            posterior = atual;
        }
        if (posterior == n || n == 0) {
            return n + " faz parte da sequencia de Fibonacci";
        } else {
            return n + " não faz parte da sequência de Fibonacci";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        System.out.print("Insira um número: ");
        String numero = scanner.nextLine();
        System.out.println(fibonacci(parseInt(numero)));
    }

}
