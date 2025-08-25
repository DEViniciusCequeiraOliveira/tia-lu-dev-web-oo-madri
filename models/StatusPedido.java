import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public enum StatusPedido {
    ACEITO,
    PREPARANDO,
    FEITO,
    AGUARDANDO_ENTREGADOR,
    SAIU_PARA_ENTREGA,
    ENTREGUE;

    public static List<StatusPedido> toList() {
        return Arrays.asList(values());
    }

    public static void mostrarTodos() {
        int numeroAtual = 1;
        for (StatusPedido status : toList()) {
            System.out.println(String.format("[%d] %s", numeroAtual, status));
            numeroAtual++;
        }
    }

    public static StatusPedido selecionar(String prompt) {
        List<StatusPedido> statusDisponiveis = StatusPedido.toList();
        Scanner sc = new Scanner(System.in);
        int numeroStatus;
        while (true) {
            mostrarTodos();
            System.out.print("\n" + prompt);
            try {
                numeroStatus = Integer.parseInt(sc.nextLine().trim());
                return statusDisponiveis.get(numeroStatus - 1);
            } catch (NumberFormatException e) {
                System.out.println("Erro: número de status inválido.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Erro: nenhum status com o número informado.");
            }
            System.out.println();
        }
    }

}