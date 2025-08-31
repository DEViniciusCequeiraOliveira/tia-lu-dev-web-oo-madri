package services;

import java.util.List;

import models.ItemCardapio;
import repository.ItemCardapioRepository;

public class ItemCardapioService {

    private ItemCardapioRepository itemCardapioRepository;

    public ItemCardapioService(ItemCardapioRepository itemCardapioRepository) {
        this.itemCardapioRepository = itemCardapioRepository;
    }

    public ItemCardapio encontrarPorCodigo(int codigoAlvo) {
        return itemCardapioRepository.findById(codigoAlvo);
    }

    public List<ItemCardapio> listarTodos() {
        return this.itemCardapioRepository.findAll();
    }

    public void registrarNovoItemCardapio(ItemCardapio itemCardapio) {
        itemCardapio.setCodigo(this.itemCardapioRepository.gerarCodigo());
        this.itemCardapioRepository.cadastrarItemCardapio(itemCardapio);
    }


}
