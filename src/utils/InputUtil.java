package utils;

import java.util.Scanner;

public class InputUtil {

    public static int capturarInt(String question, String error) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(question);
                String entrada = sc.nextLine().trim();
                if (!entrada.isBlank()) {
                    return Integer.parseInt(entrada);
                }
                System.out.println("Erro: você deve digitar algo.");
            } catch (NumberFormatException e) {
                System.out.println(error);
            }
        }
    }

    public static double capturarDouble(String question, String error) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(question);
                String entrada = sc.nextLine().trim();
                if (!entrada.isBlank()) {
                    return Double.parseDouble(entrada);
                }
                System.out.println("Erro: você deve digitar algo.");
            } catch (NumberFormatException e) {
                System.out.println(error);
            }
        }
    }

    public static String capturarString(String question, String error) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(question);
            String nome = sc.nextLine().trim();
            if (nome.isEmpty()) {
                System.out.println(error);
            } else {
                return nome;
            }
        }
    }
}
