import java.util.*;

import models.*;
import repository.ClienteRepository;
import repository.ItemCardapioRepository;
import repository.PedidoRepository;
import services.ClienteService;
import services.ItemCardapioService;
import services.PedidoService;
import utils.InputUtil;
import static utils.Constantes.*;


public class Main {
    static final String msgSemPedidos = "Nenhum pedido cadastrado.";
    static final String msgSemClientes = "Nenhum cliente cadastrado.";
    static final String msgSemItens = "Nenhum item cadastrado.";

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            ClienteService clienteService = new ClienteService(new ClienteRepository(new ArrayList<>(), new Random()));
            ItemCardapioService itemCardapioService = new ItemCardapioService(new ItemCardapioRepository(new ArrayList<>(), new Random()));
            PedidoService pedidoService = new PedidoService(new PedidoRepository(new ArrayList<>()));

            boolean saidaSolicitada = false;

            while (!saidaSolicitada) {
                printMenu();
                //
                String entrada = sc.nextLine().trim();
                switch (entrada) {
                    case "1" -> {
                        boolean voltar = false;
                        while (!voltar) {
                            printMenuItemCardapio();
                            //
                            String opc = sc.nextLine().trim();
                            switch (opc) {
                                case "1" -> {
                                    String nome = capturarNomeItemCardapio();
                                    double valor = capturarValorItemCardapio(sc);
                                    itemCardapioService.registrarNovoItemCardapio(new ItemCardapio(nome, valor));
                                    System.out.println("Item cadastrado com sucesso!\n");
                                }
                                case "2" -> {
                                    System.out.println("\n--- ITENS DO CARDÁPIO ---");
                                    if (itemCardapioService.listarTodos().isEmpty()) {
                                        System.out.println("Nenhum item cadastrado.");
                                    } else {
                                        itemCardapioService.listarTodos().forEach(System.out::println);
                                    }
                                    System.out.println();
                                }
                                case "3" -> voltar = true;
                                default -> System.out.println("Opção inválida.");
                            }
                        }
                    }

                    case "2" -> {
                        boolean voltar = false;
                        while (!voltar) {
                            printMenuCliente();
                            //
                            String opc = sc.nextLine().trim();
                            switch (opc) {
                                case "1" -> {
                                    String nome = capturarNomeCliente();
                                    String telefone = capturarTelefoneCliente();
                                    clienteService.registrarNovoCliente(new Cliente(nome, telefone));
                                    System.out.println("Cliente cadastrado com sucesso!\n");
                                }

                                case "2" -> {
                                    System.out.println("\n--- CLIENTES ---");
                                    if (clienteService.listarTodos().isEmpty()) {
                                        System.out.println("Nenhum cliente cadastrado.");
                                    } else {
                                        clienteService.listarTodos().forEach(System.out::println);
                                    }
                                    System.out.println();
                                }

                                case "3" -> voltar = true;

                                default -> System.out.println("Opção inválida.");
                            }
                        }
                    }

                    case "3" -> {
                        boolean voltar = false;
                        while (!voltar) {
                            printMenuPedido();
                            //
                            String opc = sc.nextLine().trim();
                            switch (opc) {
                                case "1" -> {
                                    String msgRetorno = validarDadosIniciaisPedido(clienteService, itemCardapioService);
                                    if (msgRetorno != null) {
                                        System.out.println(msgRetorno);
                                        break;
                                    }

                                    Cliente cliente = selecionarCliente(clienteService);
                                    System.out.println("\nPedido iniciado para " + cliente.getNome() + "...\n");

                                    Pedido pedido = new Pedido(pedidoService.obterNovoCodigo(), cliente);
                                    pedido.getItens().addAll(capturarItensPedido(sc, itemCardapioService));

                                    // Mostrar informações do pedido
                                    System.out.println("\n" + "-".repeat(70));
                                    System.out.println(pedido);
                                    System.out.println("-".repeat(70));
                                    System.out.println();
                                    System.out.print("Digite \"s\" para confirmar o pedido... ");
                                    String resposta = sc.nextLine().trim().toUpperCase();
                                    if (resposta.equals("S")) {
                                        pedidoService.cadastrarPedido(pedido);
                                        System.out.println("Pedido cadastrado com sucesso.");
                                    } else {
                                        System.out.println("Pedido cancelado.");
                                    }
                                }
                                case "2" -> {
                                    if (pedidoService.listarTodos().isEmpty()) {
                                        System.out.println(msgSemPedidos);
                                        break;
                                    }

                                    Pedido pedido = selecionarPedido(pedidoService);
                                    if (pedidoService.atualizarStatus(pedido)) {
                                        System.out.println("Status alterado de " + pedido.getStatus() + " para " + pedido.getStatus() + ".");
                                    } else {
                                        System.out.println("Status " + pedido.getStatus() + " não pôde ser alterado.");
                                    }

                                }
                                case "3" -> {
                                    // Consultar Pedido por Status
                                    if (pedidoService.listarTodos().isEmpty()) {
                                        System.out.println(msgSemPedidos);
                                        break;
                                    }

                                    // Selecionar um status válido
                                    StatusPedido status = capturarStatusPedido();

                                    // Exibir somente pedidos contendo esse status
                                    List<Pedido> pedidosStatus = pedidoService.encotrarPorStatus(status);
                                    if (pedidosStatus.isEmpty()) {
                                        System.out.println("Nenhum pedido encontrado com este status.");
                                    } else {
                                        pedidosStatus.forEach(System.out::println);
                                    }
                                }
                                case "4" -> voltar = true;
                                default -> System.out.println("Opção inválida.");
                            }
                        }
                    }

                    case "4" -> {
                        boolean voltar = false;
                        while (!voltar) {
                            System.out.println("\n--- GERENCIAR RELATÓRIOS ---");
                            System.out.println("1 - Gerar Relatório Simplificado");
                            System.out.println("2 - Gerar Relatório Detalhado");
                            System.out.println("3 - Voltar");
                            System.out.print("Escolha uma opção: ");

                            String opc = sc.nextLine().trim();

                            switch (opc) {
                                case "1" -> {
                                    // Implementação do relatório simplificado (um resumo do dia)
                                }
                                case "2" -> {
                                    // Implementação do relatório detalhado (listando os pedidos e o total de cada um)

                                }
                                case "3" -> {
                                    voltar = true;
                                }
                                default -> System.out.println("Opção inválida.");
                            }
                        }
                    }

                    case "5" -> {
                        saidaSolicitada = true;
                        System.out.println("Saindo...!");
                    }
                    default -> System.out.println("Opção inválida.");
                }
            }
        }
    }


    private static String validarDadosIniciaisPedido(ClienteService clienteService, ItemCardapioService itemCardapioService) {
        // Verificar se existem itens cadastrados
        if (itemCardapioService.listarTodos().isEmpty()) {
            return msgSemItens;
        }

        // Verificar se existem clientes cadastrados
        if (clienteService.listarTodos().isEmpty()) {
            return msgSemClientes;
        }

        return null;
    }

    private static Cliente selecionarCliente(ClienteService clienteService) {
        while (true) {
            System.out.println();
            clienteService.listarTodos().forEach(System.out::println);
            int codigoCliente = InputUtil.capturarInt("Código do cliente: ", "Erro: o código informado não é numérico.");
            Cliente cliente = clienteService.encontrarPorCodigo(codigoCliente);
            if (cliente != null) {
                return cliente;
            }
            System.out.println("Erro: nenhum cliente com o código informado.");
        }
    }

    private static Pedido selecionarPedido(PedidoService pedidoService) {
        while (true) {
            pedidoService.listarTodos().forEach(System.out::println);
            int codigoPedido = InputUtil.capturarInt("Código do pedido: ", "Erro: o código informado não é numérico.");
            Pedido pedido = pedidoService.encontrarPorCodigo(codigoPedido);
            if (pedido != null) {
                return pedido;
            }
            System.out.println("Erro: nenhum pedido com o código informado.");
        }
    }

    private static List<PedidoItemCardapio> capturarItensPedido(Scanner sc, ItemCardapioService itemCardapioService) {
        boolean atendenteFinalizou = false;
        List<PedidoItemCardapio> itens = new ArrayList<>();
        while (!atendenteFinalizou) {
            ItemCardapio item;

            while (true) {
                itemCardapioService.listarTodos();
                int codigoItem = InputUtil.capturarInt("Código do item do cardápio: ", "\nErro: o código informado não é numerico.\n");
                item = itemCardapioService.encontrarPorCodigo(codigoItem);
                if (item != null) {
                    break;
                }
                System.out.println("\nErro: nenhum item no cardápio com o código informado.\n");
            }
            int quantidade = InputUtil.capturarInt("Informe a quantidade para " + item.getNome() + ": ", "Erro: quantidade informada não é um número válido.");
            itens.add(new PedidoItemCardapio(item, quantidade));
            System.out.println("Item \"" + item.getNome() + "\" (" + quantidade + "x) adicionado com sucesso ao pedido.\n");
            System.out.print("Adicionar um novo item (digite \"n\" para finalizar)? ");
            String resposta = sc.nextLine().trim();
            atendenteFinalizou = resposta.equals("n") || resposta.equals("N");

        }
        return itens;
    }

    private static StatusPedido capturarStatusPedido() {
        List<StatusPedido> statusDisponiveis = Arrays.asList(StatusPedido.values());

        while (true) {
            int numeroAtual = 1;
            for (StatusPedido status : StatusPedido.values()) {
                System.out.printf("[%d] %s%n", numeroAtual, status);
                numeroAtual++;
            }
            int numeroEscolhido = InputUtil.capturarInt("Status a verificar: ", "Erro: número de status inválido.");
            try {
                return statusDisponiveis.get(numeroEscolhido - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Erro: nenhum status com o número informado.");
            }
            System.out.print("\n" + "Status a verificar: ");
        }
    }

    private static double capturarValorItemCardapio(Scanner sc) {
        while (true) {
            System.out.print("Valor do item (ex: 12.50): ");
            String valorStr = sc.nextLine().trim();
            try {
                double valor = Double.parseDouble(valorStr);
                if (valor < 0) {
                    System.out.println("Erro: o valor não pode ser negativo.");
                } else {
                    return valor;
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: informe um número válido (use ponto para decimal).");
            }
        }
    }

    private static String capturarNomeItemCardapio() {
        return InputUtil.capturarString("Nome do item: ", "Erro: o nome não pode ficar vazio.");
    }

    private static String capturarNomeCliente() {
        return InputUtil.capturarString("Nome do cliente: ", "Erro: o nome não pode ficar vazio.");
    }

    private static String capturarTelefoneCliente() {
        return InputUtil.capturarString("Telefone do cliente (ex: 99 99999-9999): ", "Erro: o telefone não pode ficar vazio.");
    }

}
