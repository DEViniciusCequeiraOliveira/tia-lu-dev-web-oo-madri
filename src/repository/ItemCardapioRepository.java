package repository;

import models.Cliente;
import models.ItemCardapio;

import java.util.List;
import java.util.Random;

public class ItemCardapioRepository {
    List<ItemCardapio> itensCardapio;
    Random rand;

    public ItemCardapioRepository(List<ItemCardapio> itensCardapio, Random rand) {
        this.itensCardapio = itensCardapio;
        this.rand = rand;
    }

    public List<ItemCardapio> findAll() {
        return itensCardapio;
    }

    public ItemCardapio findById(int id) {
        for (ItemCardapio itemCardapio : this.itensCardapio) {
            if (itemCardapio.getCodigo() == id) {
                return itemCardapio;
            }
        }
        return null;
    }

    public void cadastrarItemCardapio(ItemCardapio itemCardapio) {
        this.itensCardapio.add(itemCardapio);
    }

    public int gerarCodigo() {
        while (true) {
            int codigoAleatorio = rand.nextInt(9000) + 1000;
            ItemCardapio itemCardapio = findById(codigoAleatorio);

            if (itemCardapio == null) {
                return codigoAleatorio;
            }
        }
    }
}
