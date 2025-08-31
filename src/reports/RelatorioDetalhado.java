package reports;

import models.Cliente;
import models.Pedido;

import java.util.List;

public class RelatorioDetalhado {
    private Cliente cliente;
    private int codigoPedido;
    private List<PedidoItemCardapioReport> itens;
    private double precoTotal;

    public RelatorioDetalhado(Pedido pedido) {
        this.codigoPedido = pedido.getCodigo();
        this.cliente = pedido.getCliente();
        this.itens = pedido.getItens().stream().map(PedidoItemCardapioReport::new).toList();
        for (PedidoItemCardapioReport pedidoItemCardapioReport : itens) {
            this.precoTotal += pedidoItemCardapioReport.getValorTotal();
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<PedidoItemCardapioReport> getItens() {
        return itens;
    }

    public void setItens(List<PedidoItemCardapioReport> itens) {
        this.itens = itens;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }
}
