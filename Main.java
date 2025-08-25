import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            List<ItemCardapio> itens = new ArrayList<>();
            List<Cliente> clientes = new ArrayList<>();
            List<Pedido> pedidos = new ArrayList<>();
            Random rand = new Random();
            Boolean saidaSolicitada = false;

            String msgSemPedidos = "Nenhum pedido cadastrado.";
            String msgSemClientes = "Nenhum cliente cadastrado.";
            String msgSemItens = "Nenhum item cadastrado.";

            itens.add(new ItemCardapio(1029, "MACARRONADA", 10));
            clientes.add(new Cliente(4821, "DENILSON SANTOS", "75 9 0101-0202"));

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
                                        System.out.println(msgSemItens);
                                    } else {
                                        listarItens(itens);
                                    }
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
                                        System.out.println(msgSemClientes);
                                    } else {
                                        listarClientes(clientes);
                                    }
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
                                    // Verificar se existem clientes ou ítens cadastrados
                                    if (itens.isEmpty()) {
                                        System.out.println(msgSemItens);
                                        break;
                                    }
                                    if (clientes.isEmpty()) {
                                        System.out.println(msgSemClientes);
                                        break;
                                    }

                                    // Selecionar um cliente válido
                                    Cliente cliente = selecionarCliente(clientes);

                                    // Iniciar o novo pedido do cliente
                                    int codigo = obterCodigoPraNovoPedido(pedidos);
                                    Pedido pedido = new Pedido(codigo, cliente);
                                    System.out.println("\nPedido iniciado para " + cliente.getNome() + "...\n");
                                    
                                    // Selecionar os ítens do cardápio para o pedido
                                    selecionarItensProPedido(pedido, itens);

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
                                    Pedido pedido = selecionarPedido(pedidos);
                                    StatusPedido statusAtual = pedido.getStatus();
                                    pedido.atualizarStatus();
                                    System.out.println("Status alterado de " + statusAtual + " para " + pedido.getStatus() + ".");
                                }
                                case "3" -> {
                                    // Consultar Pedido por Status
                                    if (pedidos.isEmpty()) {
                                        System.out.println(msgSemPedidos);
                                        break;
                                    }
                                    
                                    // Selecionar um status válido
                                    System.out.println();
                                    StatusPedido status = StatusPedido.selecionar("Status a verificar: ");

                                    // Exibir somente pedidos contendo esse status
                                    boolean pedidoExibido = false;
                                    for (Pedido pedido: pedidos) {
                                        if (pedido.getStatus().equals(status)) {
                                            System.out.println(pedido.toString());
                                            pedidoExibido = true;
                                        }
                                    }
                                    if (!pedidoExibido) {
                                        System.out.println("Nenhum pedido encontrado com este status.");
                                    }

                                }
                                case "4" ->
                                    voltar = true;
                                default ->
                                    System.out.println("Opção inválida.");
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

                                default ->
                                    System.out.println("Opção inválida.");
                            }
                        }
                    }
                    case "5" -> {
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

    // Blocos de código modularizados para reutilização na parte de pedidos

    public static void listarClientes(List<Cliente> clientes) {
        clientes.forEach(cliente -> System.out.println(cliente));
    }

    public static void listarItens(List<ItemCardapio> itens) {
        itens.forEach(item -> System.out.println(item));
    }

    public static void listarPedidos(List<Pedido> pedidos) {
        pedidos.forEach(pedido -> System.out.println(pedido));
        System.out.println();
    }

    public static int lerNumeroIntValido(String prompt, String msgDeErro) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(msgDeErro);
            }
        }
    }

    // Métodos adicionais para a implementação do gerenciamento de pedidos: Seleção de Cliente

    public static Cliente selecionarCliente(List<Cliente> clientes) {
        while (true) {
            System.out.println();
            listarClientes(clientes);
            int codigoCliente = lerNumeroIntValido("Código do cliente: ", "Erro: o código informado não é numerico.");
            Cliente cliente = encontrarClientePorCodigo(codigoCliente, clientes);
            if (cliente != null) {
                return cliente;
            }
            System.out.println("Erro: nenhum cliente com o código informado.");
        }
    }

    public static Cliente encontrarClientePorCodigo(int codigoAlvo, List<Cliente> clientes) {
        for (Cliente cliente : clientes) {
            if (cliente.getCodigo() == codigoAlvo) {
                return cliente;
            }
        }
        return null;
    }
    
    // Métodos adicionais para a implementação do gerenciamento de pedidos: Seleção de item

    public static void selecionarItensProPedido(Pedido pedido, List<ItemCardapio> itensDoCardapio) {
        Scanner sc = new Scanner(System.in);
        Boolean atendenteFinalizou = false;
        List<PedidoItemCardapio> itensDoPedido = pedido.getItens();
        do {
            ItemCardapio item = selecionarItemCardapio(itensDoCardapio);
            int quantidade = lerNumeroIntValido("Informe a quantidade para " + item.getNome() + ": ", "Erro: quantidade informada não é um número válido.");
            itensDoPedido.add(new PedidoItemCardapio(item, quantidade));
            System.out.println("Item \"" + item.getNome() + "\" (" + quantidade + "x) adicionado com sucesso ao pedido.\n");
            System.out.print("Adicionar um novo item (digite \"n\" para finalizar)? ");
            String resposta = sc.nextLine().trim();
            atendenteFinalizou = resposta.equals("n") || resposta.equals("N");;

        } while (!atendenteFinalizou);
    }

    public static ItemCardapio selecionarItemCardapio(List<ItemCardapio> itens) {
        while (true) {
            listarItens(itens);
            int codigoItem = lerNumeroIntValido("Código do item do cardápio: ", "\nErro: o código informado não é numerico.\n");
            ItemCardapio item = encontrarItemPorCodigo(codigoItem, itens);
            if (item != null) {
                return item;
            }
            System.out.println("\nErro: nenhum item no cardápio com o código informado.\n");
        }
    }

    public static ItemCardapio encontrarItemPorCodigo(int codigoAlvo, List<ItemCardapio> itens) {
        for (ItemCardapio item : itens) {
            if (item.getCodigo() == codigoAlvo) {
                return item;
            }
        }
        return null;
    }

    public static int obterCodigoPraNovoPedido(List<Pedido> pedidos) {
        int novoCodigo = 1;
        for (Pedido pedido : pedidos) {
            int codigoPedido = pedido.getCodigo();
            if (codigoPedido >= novoCodigo) {
                novoCodigo = codigoPedido + 1;
            }
        }
        return novoCodigo;
    }

    public static Pedido selecionarPedido(List<Pedido> pedidos) {
        while (true) {
            listarPedidos(pedidos);
            int codigoPedido = lerNumeroIntValido("Código do pedido: ", "Erro: o código informado não é numérico.");
            Pedido pedido = encontrarPedidoPorCodigo(codigoPedido, pedidos);
            if (pedido != null) {
                return pedido;
            }
            System.out.println("Erro: nenhum pedido com o código informado.");
        }
    }

    public static Pedido encontrarPedidoPorCodigo(int codigoAlvo, List<Pedido> pedidos) {
        for (Pedido pedido : pedidos) {
            if (pedido.getCodigo() == codigoAlvo) {
                return pedido;
            }
        }
        return null;
    }

}