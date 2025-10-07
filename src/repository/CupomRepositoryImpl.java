package repository;

import model.CupomDeDesconto;

import java.util.ArrayList;
import java.util.List;

public class CupomRepositoryImpl implements CupomRepository {
    private final List<CupomDeDesconto> cupons = new ArrayList<>();

    @Override
    public void cadastrar(CupomDeDesconto cupom) {
        cupons.add(cupom);
        System.out.println("Cupom cadastrado com sucesso!");
    }

    @Override
    public CupomDeDesconto buscarPorCodigo(String codigo) {
        return cupons.stream()
                .filter(c -> c.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void atualizar(CupomDeDesconto cupom) {
        for (int i = 0; i < cupons.size(); i++) {
            if (cupons.get(i).getCodigo().equalsIgnoreCase(cupom.getCodigo())) {
                cupons.set(i, cupom);
                System.out.println("Cupom atualizado com sucesso!");
                return;
            }
        }
        System.out.println("Erro: Cupom não encontrado!");
    }

    @Override
    public void exibirTodos() {
        if (cupons.isEmpty()) {
            System.out.println("Nenhum cupom cadastrado!");
        } else {
            System.out.println("\n-- Cupons disponíveis --");
            cupons.forEach(System.out::println);
        }
    }

    @Override
    public List<CupomDeDesconto> listarTodos() {
        return new ArrayList<>(cupons);
    }
}