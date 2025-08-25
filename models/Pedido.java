import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class Pedido {

    private int codigo;
    private Cliente cliente;
    private StatusPedido status;
    private LocalDateTime dataHora;
    private List<PedidoItemCardapio> itens;

    // Construtores

    public Pedido() {

    }

    public Pedido(int codigo, Cliente cliente) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.status = StatusPedido.ACEITO;
        this.dataHora = LocalDateTime.now();
        this.itens = new ArrayList<>();
    }

    // Getters

    public int getCodigo() {
        return codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
    
    public StatusPedido getStatus() {
        return status;
    }

    public List<PedidoItemCardapio> getItens() {
        return itens;
    }

    // Setters

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    // Metodos Adicionais

    public boolean atualizarStatus() {
        List<StatusPedido> statusPossiveis = StatusPedido.toList();
        int indiceStatusAtual = statusPossiveis.indexOf(this.status);
        boolean ultimoStatusAlcancado = indiceStatusAtual == (statusPossiveis.size() - 1);
        if (!ultimoStatusAlcancado) {
            this.status = statusPossiveis.get(indiceStatusAtual + 1);
            return true;
        }
        return false;
    }

    public String toString() {
        DateTimeFormatter formatoDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String cabecalhoTemplate = "Pedido: %d | Horário: %s | Status: %s";
        String cabecalho = String.format(cabecalhoTemplate, codigo, dataHora.format(formatoDataHora), status);
        String stringInstancia = "-".repeat(70) + "\n" + cabecalho + "\n" + this.cliente.toString();
        for (PedidoItemCardapio pedidoItem : itens) {
            stringInstancia += "\n" + "- " + pedidoItem.toString();
        }
        stringInstancia += "\n" + "-".repeat(70);
        return stringInstancia;
    }

}