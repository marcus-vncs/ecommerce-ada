package repository;

import model.Cliente;

import java.util.List;

public interface ClienteRepository {
    void salvar(Cliente cliente);
    Cliente buscarPorDocumento(String documento);
    void atualizar(String documento, String novoNome);
    List<Cliente> listarTodos();
    void exibirTodos();
}

