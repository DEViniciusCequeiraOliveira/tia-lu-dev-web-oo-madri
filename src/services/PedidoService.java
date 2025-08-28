package services;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import models.ItemCardapio;
import models.Pedido;
import models.PedidoItemCardapio;
import models.StatusPedido;
import utils.InputValidador;

public class PedidoService {
    List<Pedido> pedidos;

    public PedidoService(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Pedido encontrarPorCodigo(int codigoAlvo) {
        for (Pedido pedido : this.pedidos) {
            if (pedido.getCodigo() == codigoAlvo) {
                return pedido;
            }
        }
        return null;
    }

    public void listarTodos() {
        this.pedidos.forEach(System.out::println);
        System.out.println();
    }

    public int obterNovoCodigo() {
        int novoCodigo = 1;
        for (Pedido pedido : this.pedidos) {
            int codigoPedido = pedido.getCodigo();
            if (codigoPedido >= novoCodigo) {
                novoCodigo = codigoPedido + 1;
            }
        }
        return novoCodigo;
    }

    public Pedido selecionar() {
        while (true) {
            listarTodos();
            int codigoPedido = InputValidador.lerInt("Código do pedido: ", "Erro: o código informado não é numérico.");
            Pedido pedido = encontrarPorCodigo(codigoPedido);
            if (pedido != null) {
                return pedido;
            }
            System.out.println("Erro: nenhum pedido com o código informado.");
        }
    }

    public static boolean atualizarStatus(Pedido pedido) {
        List<StatusPedido> statusPossiveis = Arrays.asList(StatusPedido.values());
        int indiceStatusAtual = statusPossiveis.indexOf(pedido.getStatus());
        boolean ultimoStatusAlcancado = indiceStatusAtual == (statusPossiveis.size() - 1);
        if (!ultimoStatusAlcancado) {
            pedido.setStatus(statusPossiveis.get(indiceStatusAtual + 1));
            return true;
        }
        return false;
    }

    public static void cadastrarItens(Pedido pedido, ItemCardapioService itemCardapioService) {
        Scanner sc = new Scanner(System.in);
        boolean atendenteFinalizou = false;
        List<PedidoItemCardapio> itensDoPedido = pedido.getItens();
        do {
            ItemCardapio item;

            while (true) {
                //todo: colocar print aqui
                //listarTodos();
                int codigoItem = InputValidador.lerInt("Código do item do cardápio: ", "\nErro: o código informado não é numerico.\n");
                item = itemCardapioService.encontrarPorCodigo(codigoItem);
                if (item != null) {
                    break;
                }
                System.out.println("\nErro: nenhum item no cardápio com o código informado.\n");
            }
            int quantidade = InputValidador.lerInt("Informe a quantidade para " + item.getNome() + ": ", "Erro: quantidade informada não é um número válido.");
            itensDoPedido.add(new PedidoItemCardapio(item, quantidade));
            System.out.println("Item \"" + item.getNome() + "\" (" + quantidade + "x) adicionado com sucesso ao pedido.\n");
            System.out.print("Adicionar um novo item (digite \"n\" para finalizar)? ");
            String resposta = sc.nextLine().trim();
            atendenteFinalizou = resposta.equals("n") || resposta.equals("N");

        } while (!atendenteFinalizou);
    }

}