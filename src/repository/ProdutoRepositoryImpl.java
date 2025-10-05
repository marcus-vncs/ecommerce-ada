package repository;

import model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoRepositoryImpl implements ProdutoRepository {
    private final List<Produto> produtos = new ArrayList<>();

    @Override
    public void salvar(Produto produto) {
        for (Produto p : produtos) {
            if (p.getId() == produto.getId()) {
                System.out.println("Erro: ID já cadastrado!");
                return;
            }
        }
        produtos.add(produto);
        System.out.println("Produto salvo com sucesso!");
    }

    @Override
    public Produto buscarPorId(int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        System.out.println("Erro: Produto não encontrado!");
        return null;
    }

    @Override
    public void atualizar(int id, String novoNome, double novoPreco) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                produto.setNome(novoNome);
                produto.setPreco(novoPreco);
                System.out.println("Produto atualizado com sucesso!");
                return;
            }
        }
        System.out.println("Erro: Produto não encontrado!");
    }

    @Override
    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos); // apenas retorna
    }

    @Override
    public void exibirTodos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado!");
        } else {
            System.out.println("\n-- Produtos cadastrados --");
            for (Produto p : produtos) {
                System.out.println(p);
            }
        }
    }

}
