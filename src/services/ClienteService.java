package services;

import java.util.List;
import models.Cliente;
import repository.ClienteRepository;

public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente encontrarPorCodigo(int codigoAlvo) {
        return this.clienteRepository.findById(codigoAlvo);
    }

    public List<Cliente> listarTodos() {
        return this.clienteRepository.findAll();
    }

    public void registrarNovoCliente(Cliente cliente) {                 
        cliente.setCodigo(clienteRepository.gerarCodigo());
        clienteRepository.cadastrarCliente(cliente);
        
    }

}
