package services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import models.Pedido;
import models.PedidoItemCardapio;
import models.StatusPedido;
import reports.RelatorioDetalhado;
import reports.RelatorioSimplificado;
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

    public RelatorioSimplificado relatorioSimplificado() {
        int quantidade = 0;
        double valorArrecadado = 0;
        for (Pedido pedido : this.pedidoRepository.findAll()) {
            if (pedido.getDataHora().toLocalDate().equals(LocalDate.now())) {
                quantidade += 1;
                for (PedidoItemCardapio pedidoItemCardapio : pedido.getItens()) {
                    valorArrecadado += pedidoItemCardapio.getQuantidade() * pedidoItemCardapio.getItemCardapio().getValor();
                }

            }
        }

        return new RelatorioSimplificado(quantidade, valorArrecadado);
    }

    public List<RelatorioDetalhado> relatorioDetalhado() {
        List<RelatorioDetalhado> dados = new ArrayList<>();

        for (Pedido pedido : this.pedidoRepository.findAll()) {
            if (pedido.getDataHora().toLocalDate().equals(LocalDate.now())) {
                dados.add(new RelatorioDetalhado(pedido));
            }
        }

        return dados;
    }
}