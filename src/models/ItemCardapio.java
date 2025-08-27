package models;

public class ItemCardapio {
    private int codigo;
    private String nome;
    private double valor;

    public ItemCardapio() {

    }

    public ItemCardapio(int codigo, String nome, double valor) {
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String toString() {
        return String.format("Código: %d | Nome: %s | Valor: R$ %.2f", codigo, nome, valor);
    }
}