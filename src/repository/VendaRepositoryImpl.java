package repository;

import model.Venda;

import java.util.ArrayList;
import java.util.List;

public class VendaRepositoryImpl implements VendaRepository {
    private final List<Venda> vendas = new ArrayList<>();

    @Override
    public void salvar(Venda venda) {
        vendas.add(venda);
        System.out.println("Venda salva com sucesso!");
    }

    @Override
    public Venda buscarPorId(int id) {
        for (Venda venda : vendas) {
            if (venda.getId() == id) {
                return venda;
            }
        }
        System.out.println("Erro: Venda não encontrada!");
        return null;
    }

    @Override
    public List<Venda> listarTodos() {
        return new ArrayList<>(vendas); // apenas retorna
    }

    @Override
    public void exibirTodos() {
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada!");
        } else {
            System.out.println("\n-- Vendas registradas --");
            for (Venda v : vendas) {
                System.out.println(v);
            }
        }
    }
}
