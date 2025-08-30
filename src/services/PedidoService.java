package services;

import java.util.Arrays;
import java.util.List;

import models.Pedido;
import models.StatusPedido;
import repository.PedidoRepository;

public class PedidoService {
    private PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido encontrarPorCodigo(int codigoAlvo) {
        return this.pedidoRepository.findById(codigoAlvo);
    }

    public List<Pedido> encotrarPorStatus(StatusPedido statusPedido) {
        return this.pedidoRepository.findByStatus(statusPedido);
    }

    public List<Pedido> listarTodos() {
        return this.pedidoRepository.findAll();
    }

    public int obterNovoCodigo() {
        return this.pedidoRepository.gerarCodigo();
    }

    public void cadastrarPedido(Pedido pedido) {
        pedidoRepository.create(pedido);
    }

    public boolean atualizarStatus(Pedido pedido) {
        List<StatusPedido> statusPossiveis = Arrays.asList(StatusPedido.values());
        int indiceStatusAtual = statusPossiveis.indexOf(pedido.getStatus());
        boolean ultimoStatusAlcancado = indiceStatusAtual == (statusPossiveis.size() - 1);
        if (!ultimoStatusAlcancado) {
            pedidoRepository.updateStatus(pedido, statusPossiveis.get(indiceStatusAtual + 1));
            return true;
        }
        return false;
    }

}