package repository;

import java.util.List;
import java.util.Random;
import models.Cliente;

public class ClienteRepository {

    List<Cliente> clientes;
    Random rand;

    public ClienteRepository(List<Cliente> clientes, Random rand) {
        this.clientes = clientes;
        this.rand = rand;
    }

    public List<Cliente> findAll() {
        return clientes;
    }

    public Cliente findById(int id) {
        for (Cliente cliente : this.clientes) {
            if (cliente.getCodigo() == id) {
                return cliente;
            }
        }
        return null;
    }

    public void cadastrarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public int gerarCodigo() {
        while (true) {
            int codigoAleatorio = rand.nextInt(9000) + 1000;
            Cliente cliente = findById(codigoAleatorio);

            if (cliente == null) {
                return codigoAleatorio;
            }
        }
    }

}
