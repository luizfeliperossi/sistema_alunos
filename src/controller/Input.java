package controller;

import java.util.Scanner;

public class Input {

    private static Scanner teclado = new Scanner(System.in);

    public static Scanner getTeclado() {
        return teclado;
    }

    public static String next() {
        return teclado.next();
    }

    public static String nextLine() {
        return teclado.nextLine();
    }

    public static int nextInt() {
        while (true) {
            String valor = "";

            try {
                valor = teclado.next();
                return Integer.parseInt(valor);
            } catch (NumberFormatException e) {
                System.out.println("Valor Invalido");
            }
        }
    }

    public static boolean nextBoolean() {
        while (true) {
            String valor = "";
            try {
                valor = teclado.next();
                if (valor.equalsIgnoreCase("sim")) {
                    return true;
                } else if (valor.equalsIgnoreCase("nao")) {
                    return false;
                }
                System.out.println("Valor invalido");
            } catch (Exception e) {
                System.out.println("Valor invalido");
            }
        }
    }
}