package utils;

import java.util.Scanner;

public class InputValidador {

    public static int lerInt(String prompt, String msgDeErro) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(prompt);
                String entrada = sc.nextLine().trim();
                if (!entrada.isBlank()) {
                    return Integer.parseInt(entrada);
                }
                System.out.println("Erro: você deve digitar algo.");
            } catch (NumberFormatException e) {
                System.out.println(msgDeErro);
            }
        }
    }

}