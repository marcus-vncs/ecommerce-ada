package repository;

import model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepositoryImpl implements ClienteRepository {
    private final List<Cliente> clientes = new ArrayList<>();

    @Override
    public void salvar(Cliente cliente) {
        for (Cliente c : clientes) {
            if (c.getDocumento().equals(cliente.getDocumento())) {
                System.out.println("Erro: Documento já cadastrado!");
                return;
            }
        }
        clientes.add(cliente);
        System.out.println("Cliente salvo com sucesso!");
    }

    @Override
    public Cliente buscarPorDocumento(String documento) {
        for (Cliente cliente : clientes) {
            if (cliente.getDocumento().equals(documento)) {
                return cliente;
            }
        }
        System.out.println("Erro: Cliente não encontrado!");
        return null;
    }

    @Override
    public void atualizar(String documento, String novoNome) {
        for (Cliente cliente : clientes) {
            if (cliente.getDocumento().equals(documento)) {
                cliente.setNome(novoNome);
                System.out.println("Cliente atualizado com sucesso!");
                return;
            }
        }
        System.out.println("Erro: Cliente não encontrado!");
    }

    @Override
    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes); // só retorna
    }

    @Override
    public void exibirTodos() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado!");
        } else {
            System.out.println("\n-- Clientes cadastrados --");
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

}
