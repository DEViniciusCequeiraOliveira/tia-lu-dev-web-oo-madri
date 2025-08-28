
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import models.Cliente;
import models.ItemCardapio;
import models.Pedido;
import models.StatusPedido;
import repository.ClienteRepository;
import repository.ItemCardapioRepository;
import services.ClienteService;
import services.ItemCardapioService;
import services.PedidoService;
import services.StatusPedidoService;
import utils.InputValidador;

public class Main {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            List<Pedido> pedidos = new ArrayList<>();

            ClienteService clienteService = new ClienteService(new ClienteRepository(new ArrayList<>(), new Random()));
            ItemCardapioService itemCardapioService = new ItemCardapioService(new ItemCardapioRepository(new ArrayList<>(), new Random()));

            PedidoService pedidoServices = new PedidoService(pedidos);

            boolean saidaSolicitada = false;

            String msgSemPedidos = "Nenhum pedido cadastrado.";
            String msgSemClientes = "Nenhum cliente cadastrado.";
            String msgSemItens = "Nenhum item cadastrado.";


            while (!saidaSolicitada) {
                System.out.println("==== MENU PRINCIPAL ====");
                System.out.println("1 - Gerenciar Cardápio");
                System.out.println("2 - Gerenciar Clientes");
                System.out.println("3 - Gerenciar Pedidos");
                System.out.println("4 - Gerenciar Relatórios");
                System.out.println("5 - Sair");
                System.out.print("Escolha uma opção: ");

                String entrada = sc.nextLine().trim();

                switch (entrada) {
                    case "1" -> {
                        boolean voltar = false;
                        while (!voltar) {
                            System.out.println("\n--- GERENCIAR CARDÁPIO ---");
                            System.out.println("1 - Cadastrar Novo Item");
                            System.out.println("2 - Listar Itens");
                            System.out.println("3 - Voltar");
                            System.out.print("Escolha uma opção: ");

                            String opc = sc.nextLine().trim();
                            switch (opc) {
                                case "1" -> {
                                    String nome = capturarNomeItemCardapio(sc);
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
                            System.out.println("\n--- GERENCIAR CLIENTES ---");
                            System.out.println("1 - Cadastrar Novo Cliente");
                            System.out.println("2 - Listar Clientes");
                            System.out.println("3 - Voltar");
                            System.out.print("Escolha uma opção: ");

                            String opc = sc.nextLine().trim();

                            switch (opc) {
                                case "1" -> {
                                    String nome = capturarNomeCliente(sc);
                                    String telefone = capturarTelefoneCliente(sc);
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
                                }

                                case "3" -> voltar = true;

                                default -> System.out.println("Opção inválida.");
                            }
                        }
                    }

                    case "3" -> {
                        boolean voltar = false;
                        while (!voltar) {
                            System.out.println("\n--- GERENCIAR PEDIDOS ---");
                            System.out.println("1 - Cadastrar Novo Pedido");
                            System.out.println("2 - Atualizar Status do Pedido");
                            System.out.println("3 - Consultar Pedido por Status");
                            System.out.println("4 - Voltar");
                            System.out.print("Escolha uma opção: ");

                            String opc = sc.nextLine().trim();

                            switch (opc) {
                                case "1" -> {

                                    // Verificar se existem clientes ou itens cadastrados
                                    if (itemCardapioService.listarTodos().isEmpty()) {
                                        System.out.println(msgSemItens);
                                        break;
                                    }
                                    if (clienteService.listarTodos().isEmpty()) {
                                        System.out.println(msgSemClientes);
                                        break;
                                    }

                                    // Selecionar um cliente válido
                                    Cliente cliente;
                                    while (true) {
                                        System.out.println();
                                        clienteService.listarTodos().forEach(System.out::println);
                                        int codigoCliente = InputValidador.lerInt("Código do cliente: ", "Erro: o código informado não é numérico.");
                                        cliente = clienteService.encontrarPorCodigo(codigoCliente);
                                        if (cliente != null) {
                                            break;
                                        }
                                        System.out.println("Erro: nenhum cliente com o código informado.");
                                    }

                                    // Iniciar o novo pedido do cliente
                                    int codigo = pedidoServices.obterNovoCodigo();
                                    Pedido pedido = new Pedido(codigo, cliente);
                                    System.out.println("\nPedido iniciado para " + cliente.getNome() + "...\n");

                                    // Selecionar os itens do cardápio para o pedido
                                    PedidoService.cadastrarItens(pedido, itemCardapioService);

                                    // Mostrar informações do pedido
                                    System.out.println("\n" + "-".repeat(70));
                                    System.out.println(pedido.toString());
                                    System.out.println("-".repeat(70));
                                    System.out.println();
                                    System.out.print("Digite \"s\" para confirmar o pedido... ");
                                    String resposta = sc.nextLine().trim();
                                    if (resposta.equals("s") || resposta.equals("S")) {
                                        pedidos.add(pedido);
                                        System.out.println("Pedido cadastrado com sucesso.");
                                    } else {
                                        System.out.println("Pedido cancelado.");
                                    }

                                }
                                case "2" -> {
                                    if (pedidos.isEmpty()) {
                                        System.out.println(msgSemPedidos);
                                        break;
                                    }
                                    Pedido pedido = pedidoServices.selecionar();
                                    StatusPedido statusAtual = pedido.getStatus();
                                    if (PedidoService.atualizarStatus(pedido)) {
                                        System.out.println("Status alterado de " + statusAtual + " para " + pedido.getStatus() + ".");
                                    } else {
                                        System.out.println("Status " + statusAtual + " não pôde ser alterado.");
                                    }

                                }
                                case "3" -> {
                                    // Consultar Pedido por Status
                                    if (pedidos.isEmpty()) {
                                        System.out.println(msgSemPedidos);
                                        break;
                                    }

                                    // Selecionar um status válido
                                    System.out.println();
                                    StatusPedido status = StatusPedidoService.selecionar("Status a verificar: ");

                                    // Exibir somente pedidos contendo esse status
                                    boolean pedidoExibido = false;
                                    for (Pedido pedido : pedidos) {
                                        if (pedido.getStatus().equals(status)) {
                                            System.out.println(pedido.toString());
                                            pedidoExibido = true;
                                        }
                                    }
                                    if (!pedidoExibido) {
                                        System.out.println("Nenhum pedido encontrado com este status.");
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
                        break;
                    }
                    default -> System.out.println(
                            "Opção inválida.");
                }
            }
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

    private static String capturarNomeItemCardapio(Scanner sc) {
        while (true) {
            System.out.print("Nome do item: ");
            String nome = sc.nextLine().trim();
            if (nome.isEmpty()) {
                System.out.println("Erro: o nome não pode ficar vazio.");
            } else {
                return nome;
            }
        }
    }

    private static String capturarNomeCliente(Scanner sc) {
        while (true) {
            System.out.print("Nome do cliente: ");
            String nome = sc.nextLine().trim();
            if (nome.isEmpty()) {
                System.out.println("Erro: o nome não pode ficar vazio.");
            } else {
                return nome;
            }
        }
    }

    private static String capturarTelefoneCliente(Scanner sc) {
        while (true) {
            System.out.print("Telefone do cliente (ex: 99 99999-9999): ");
            String telefone = sc.nextLine().trim();
            if (telefone.isEmpty()) {
                System.out.println("Erro: o telefone não pode ficar vazio.");
            } else {
                return telefone;
            }

        }
    }

}
