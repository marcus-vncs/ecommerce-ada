package repository;

import model.CupomDeDesconto;

import java.util.List;

public interface CupomRepository {
    void cadastrar (CupomDeDesconto cupomDeDesconto);
    CupomDeDesconto buscarPorCodigo(String codigo);
    void atualizar (CupomDeDesconto cupomDeDesconto);
    void exibirTodos();
    List<CupomDeDesconto> listarTodos();
}
