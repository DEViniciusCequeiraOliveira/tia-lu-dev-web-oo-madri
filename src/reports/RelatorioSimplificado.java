package reports;

public class RelatorioSimplificado {
    private int quantidade;
    private double valorArrecadado;

    public RelatorioSimplificado(int quantidade, double valorArrecadado) {
        this.quantidade = quantidade;
        this.valorArrecadado = valorArrecadado;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorArrecadado() {
        return valorArrecadado;
    }

    public void setValorArrecadado(double valorArrecadado) {
        this.valorArrecadado = valorArrecadado;
    }
}
