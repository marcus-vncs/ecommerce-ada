package repository;

import model.Venda;

import java.util.List;

public interface VendaRepository {
    void salvar(Venda venda);
    Venda buscarPorId(int id);
    List<Venda> listarTodos();
    void exibirTodos();
}
