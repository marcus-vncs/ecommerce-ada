package repository;

import model.Produto;

import java.util.List;

public interface ProdutoRepository {
    void salvar(Produto produto);
    Produto buscarPorId(int id);
    void atualizar(int id, String novoNome, double novoPreco);
    List<Produto> listarTodos();
    void exibirTodos();
}
