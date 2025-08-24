
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            List<ItemCardapio> itens = new ArrayList<>();
            List<Cliente> clientes = new ArrayList<>();
            Random rand = new Random();
            Boolean saidaSolicitada = false;

            while (!saidaSolicitada) {
                System.out.println("==== MENU PRINCIPAL ====");
                System.out.println("1 - Gerenciar Cardápio");
                System.out.println("2 - Gerenciar Clientes");
                System.out.println("3 - Gerenciar Pedidos");
                System.out.println("4 - Sair");
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
                                    String nome;
                                    while (true) {
                                        System.out.print("Nome do item: ");
                                        nome = sc.nextLine().trim();
                                        if (nome.isEmpty()) {
                                            System.out.println("Erro: o nome não pode ficar vazio.");
                                        } else {
                                            break;
                                        }
                                    }

                                    double valor;
                                    while (true) {
                                        System.out.print("Valor do item (ex: 12.50): ");
                                        String valorStr = sc.nextLine().trim();
                                        try {
                                            valor = Double.parseDouble(valorStr);
                                            if (valor < 0) {
                                                System.out.println("Erro: o valor não pode ser negativo.");
                                            } else {
                                                break;
                                            }
                                        } catch (NumberFormatException e) {
                                            System.out.println("Erro: informe um número válido (use ponto para decimal).");
                                        }
                                    }
                                    int codigoAleatorio;
                                    while (true) {
                                        codigoAleatorio = rand.nextInt(9000) + 1000;
                                        boolean existe = false;
                                        for (ItemCardapio it : itens) {
                                            if (it.getCodigo() == codigoAleatorio) {
                                                existe = true;
                                                break;
                                            }
                                        }
                                        if (!existe) {
                                            break;
                                        }
                                    }

                                    ItemCardapio novo = new ItemCardapio(codigoAleatorio, nome, valor);
                                    itens.add(novo);
                                    System.out.println("Item cadastrado com sucesso!\n");
                                }

                                case "2" -> {
                                    System.out.println("\n--- ITENS DO CARDÁPIO ---");
                                    if (itens.isEmpty()) {
                                        System.out.println("Nenhum item cadastrado.");
                                    } else {
                                        for (ItemCardapio it : itens) {
                                            System.out.printf("Código: %d | Nome: %s | Valor: R$ %.2f%n",
                                                    it.getCodigo(), it.getNome(), it.getValor());
                                        }
                                    }
                                    System.out.println();
                                }

                                case "3" ->
                                    voltar = true;

                                default ->
                                    System.out.println("Opção inválida.");
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
                                    String nome;
                                    while (true) {
                                        System.out.print("Nome do cliente: ");
                                        nome = sc.nextLine().trim();
                                        if (nome.isEmpty()) {
                                            System.out.println("Erro: o nome não pode ficar vazio.");
                                        } else {
                                            break;
                                        }
                                    }

                                    String telefone;
                                    while (true) {
                                        System.out.print("Telefone do cliente (ex: 99 99999-9999): ");
                                        telefone = sc.nextLine().trim();
                                        if (telefone.isEmpty()) {
                                            System.out.println("Erro: o telefone não pode ficar vazio.");
                                        } else {
                                            break;
                                        }
                                    }
                                    int codigoAleatorio;
                                    while (true) {
                                        codigoAleatorio = rand.nextInt(9000) + 1000;
                                        boolean existe = false;
                                        for (Cliente cl : clientes) {
                                            if (cl.getCodigo() == codigoAleatorio) {
                                                existe = true;
                                                break;
                                            }
                                        }
                                        if (!existe) {
                                            break;
                                        }
                                    }

                                    Cliente novo = new Cliente(codigoAleatorio, nome, telefone);
                                    clientes.add(novo);
                                    System.out.println("Cliente cadastrado com sucesso!\n");
                                }

                                case "2" -> {
                                    System.out.println("\n--- CLIENTES ---");
                                    if (clientes.isEmpty()) {
                                        System.out.println("Nenhum cliente cadastrado.");
                                    } else {
                                        for (Cliente cl : clientes) {
                                            System.out.printf("Código: %d | Nome: %s | Telefone: %s%n",
                                                    cl.getCodigo(), cl.getNome(), cl.getTelefone());
                                        }
                                    }
                                    System.out.println();
                                }

                                case "3" ->
                                    voltar = true;

                                default ->
                                    System.out.println("Opção inválida.");
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
                                    //Cadastrar Pedido
                                }
                                case "2" -> {
                                    //Atualizar Status do Pedido
                                }
                                case "3" -> {
                                    //Consultar Pedido por Status
                                }
                                case "4" ->
                                    voltar = true;
                                default ->
                                    System.out.println("Opção inválida.");
                            }
                        }
                    }
                    case "4" -> {
                        saidaSolicitada = true;
                        System.out.println("Saindo...!");
                        break;
                    }
                    default ->
                        System.out.println("Opção inválida.");
                }
            }
        }
    }
}
