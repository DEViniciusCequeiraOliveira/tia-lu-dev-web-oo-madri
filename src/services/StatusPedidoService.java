package services;

import java.util.Arrays;
import java.util.List;

import models.Pedido;
import models.StatusPedido;

public class StatusPedidoService {

    public StatusPedidoService() {

    }

    public boolean atualizarStatus(Pedido pedido) {
        List<StatusPedido> statusPossiveis = Arrays.asList(StatusPedido.values());
        int indiceStatusAtual = statusPossiveis.indexOf(pedido.getStatus());
        boolean ultimoStatusAlcancado = indiceStatusAtual == (statusPossiveis.size() - 1);
        if (!ultimoStatusAlcancado) {
            pedido.setStatus(statusPossiveis.get(indiceStatusAtual + 1));
            return true;
        }
        return false;
    }

}
