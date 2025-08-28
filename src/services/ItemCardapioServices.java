package services;

import java.util.List;

import models.ItemCardapio;
import utils.InputValidador;

public class ItemCardapioServices {
    List<ItemCardapio> itens;

    public ItemCardapioServices(List<ItemCardapio> itens) {
        this.itens = itens;
    }

    public ItemCardapio encontrarPorCodigo(int codigoAlvo) {
        for (ItemCardapio item : this.itens) {
            if (item.getCodigo() == codigoAlvo) {
                return item;
            }
        }
        return null;
    }

    public void listarTodos() {
        itens.forEach(System.out::println);
    }

    public ItemCardapio selecionar() {
        while (true) {
            listarTodos();
            int codigoItem = InputValidador.lerInt("Código do item do cardápio: ", "\nErro: o código informado não é numerico.\n");
            ItemCardapio item = encontrarPorCodigo(codigoItem);
            if (item != null) {
                return item;
            }
            System.out.println("\nErro: nenhum item no cardápio com o código informado.\n");
        }
    }


}