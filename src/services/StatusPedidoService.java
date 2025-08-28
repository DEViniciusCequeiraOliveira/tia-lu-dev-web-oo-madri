package services;

import java.util.Arrays;
import java.util.List;
import models.StatusPedido;
import utils.InputValidador;

public class StatusPedidoService {

    public static void mostrarTodos() {
        int numeroAtual = 1;
        for (StatusPedido status : StatusPedido.values()) {
            System.out.printf("[%d] %s%n", numeroAtual, status);
            numeroAtual++;
        }
    }

    public static StatusPedido selecionar(String prompt) {
        List<StatusPedido> statusDisponiveis = Arrays.asList(StatusPedido.values());
        while (true) {
            mostrarTodos();
            int numeroEscolhido = InputValidador.lerInt(prompt, "Erro: número de status inválido.");
            try {
                return statusDisponiveis.get(numeroEscolhido - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Erro: nenhum status com o número informado.");
            }
            System.out.print("\n" + prompt);
        }
    }
}
