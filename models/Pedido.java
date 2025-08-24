class Pedido {
    private int codigo;
    private Cliente cliente;
    private StatusPedido status;
    private LocalDateTime dataHora;
    private List<PedidoItemCardapio> itens;

    public Pedido() {

    }

    public Pedido(int codigo, Cliente cliente) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.status = StatusPedido.ACEITO; // status inicial
        this.dataHora = LocalDateTime.now();
        this.itens = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public List<PedidoItemCardapio> getItens() {
        return itens;
    }
}
