package models;

public class PedidoItemCardapio {
    private ItemCardapio itemCardapio;
    private int quantidade;

    public PedidoItemCardapio(ItemCardapio itemCardapio, int quantidade) {
        this.itemCardapio = itemCardapio;
        this.quantidade = quantidade;
    }

    public ItemCardapio getItemCardapio() {
        return itemCardapio;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setItemCardapio(ItemCardapio itemCardapio) {
        this.itemCardapio = itemCardapio;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String toString() {
        ItemCardapio item = this.itemCardapio;
        return String.format("%s [R$ %.2f] (x%d)", item.getNome(), item.getValor(), this.quantidade);
    }
}