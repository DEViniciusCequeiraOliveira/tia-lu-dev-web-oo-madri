package utils;

public class Constantes {
    public static void printMenu() {
        System.out.println("==== MENU PRINCIPAL ====");
        System.out.println("1 - Gerenciar Cardápio");
        System.out.println("2 - Gerenciar Clientes");
        System.out.println("3 - Gerenciar Pedidos");
        System.out.println("4 - Gerenciar Relatórios");
        System.out.println("5 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void printMenuCliente() {
        System.out.println("\n--- GERENCIAR CLIENTES ---");
        System.out.println("1 - Cadastrar Novo Cliente");
        System.out.println("2 - Listar Clientes");
        System.out.println("3 - Voltar");
        System.out.print("Escolha uma opção: ");
    }

    public static void printMenuItemCardapio() {
        System.out.println("\n--- GERENCIAR CARDÁPIO ---");
        System.out.println("1 - Cadastrar Novo Item");
        System.out.println("2 - Listar Itens");
        System.out.println("3 - Voltar");
        System.out.print("Escolha uma opção: ");
    }

    public static void printMenuPedido() {
        System.out.println("\n--- GERENCIAR PEDIDOS ---");
        System.out.println("1 - Cadastrar Novo Pedido");
        System.out.println("2 - Atualizar Status do Pedido");
        System.out.println("3 - Consultar Pedido por Status");
        System.out.println("4 - Voltar");
        System.out.print("Escolha uma opção: ");
    }
}
