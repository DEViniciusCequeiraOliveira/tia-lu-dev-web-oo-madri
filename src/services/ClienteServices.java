package services;

import java.util.List;

import models.Cliente;
import utils.InputValidador;

public class ClienteServices {
    List<Cliente> clientes;

    public ClienteServices(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente encontrarPorCodigo(int codigoAlvo) {
        for (Cliente cliente : this.clientes) {
            if (cliente.getCodigo() == codigoAlvo) {
                return cliente;
            }
        }
        return null;
    }

    public void listarTodos() {
        this.clientes.forEach(System.out::println);
    }

    public Cliente selecionar() {
        while (true) {
            System.out.println();
            listarTodos();
            int codigoCliente = InputValidador.lerInt("Código do cliente: ", "Erro: o código informado não é numérico.");
            Cliente cliente = encontrarPorCodigo(codigoCliente);
            if (cliente != null) {
                return cliente;
            }
            System.out.println("Erro: nenhum cliente com o código informado.");
        }
    }
}