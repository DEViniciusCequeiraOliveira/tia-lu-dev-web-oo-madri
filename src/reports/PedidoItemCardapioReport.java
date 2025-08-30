package reports;

import models.ItemCardapio;
import models.PedidoItemCardapio;

public class PedidoItemCardapioReport {
    private int idCardapio;
    private String nome;
    private int quantidade;
    private double valor;
    private double valorTotal;

    public PedidoItemCardapioReport(PedidoItemCardapio pedidoItemCardapio) {
        this.idCardapio = pedidoItemCardapio.getItemCardapio().getCodigo();
        this.nome = pedidoItemCardapio.getItemCardapio().getNome();
        this.quantidade = pedidoItemCardapio.getQuantidade();
        this.valor = pedidoItemCardapio.getItemCardapio().getValor();
        this.valorTotal = pedidoItemCardapio.getQuantidade() * pedidoItemCardapio.getItemCardapio().getValor();
    }

    public int getIdCardapio() {
        return idCardapio;
    }

    public void setIdCardapio(int idCardapio) {
        this.idCardapio = idCardapio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
