package repository;

import models.Pedido;
import models.StatusPedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoRepository {

    List<Pedido> pedidos;

    public PedidoRepository(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Pedido> findAll() {
        return this.pedidos;
    }

    public Pedido findById(int id) {
        for (Pedido pedido : this.pedidos) {
            if (pedido.getCodigo() == id) {
                return pedido;
            }
        }
        return null;
    }

    public List<Pedido> findByStatus(StatusPedido status) {
        List<Pedido> pedidosStatus = new ArrayList<>();
        for (Pedido pedido : this.pedidos) {
            if (pedido.getStatus().equals(status)) {
                pedidosStatus.add(pedido);
            }
        }
        return pedidosStatus;
    }

    public int gerarCodigo() {
        int maiorCodigo = 0;
        for (Pedido pedido : this.pedidos) {
            if (pedido.getCodigo() > maiorCodigo) {
                maiorCodigo = pedido.getCodigo();
            }
        }
        return ++maiorCodigo;
    }

    public void create(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    public void updateStatus(Pedido pedido, StatusPedido statusPedido) {
        pedido.setStatus(statusPedido);
    }
}
