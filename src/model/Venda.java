package model;

import service.NotificacaoService;

import java.util.ArrayList;
import java.util.List;

public class Venda {
    private static int contadorId = 1;

    private final int id;
    private final Cliente cliente;
    private final List<ItemVenda> itens;
    private String status;

    public Venda(Cliente cliente) {
        this.id = contadorId++;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.status = "Aberto";
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void adicionarItem(Produto produto, int quantidade, double valorVenda) {
        if (!status.equals("Aberto")) {
            throw new IllegalStateException("Venda não está aberta para adicionar itens!");
        }
        itens.add(new ItemVenda(produto, quantidade, valorVenda));
    }

    public double calcularValorTotal() {
        return itens.stream()
                .mapToDouble(ItemVenda::calcularSubtotal)
                .sum();
    }

    public void finalizar(NotificacaoService notificacaoService) {
        if (itens.isEmpty() || calcularValorTotal() <= 0) {
            throw new IllegalStateException("Venda inválida! Não pode ser finalizada.");
        }
        this.status = "Aguardando pagamento";
        notificacaoService.notificar(cliente, "Sua venda foi finalizada. Aguardando pagamento.");
    }

    public void entregar(NotificacaoService notificacaoService) {
        if (!status.equals("Pago")) {
            throw new IllegalStateException("Venda não pode ser entregue sem pagamento!");
        }
        this.status = "Finalizado";
        notificacaoService.notificar(cliente, "Seu pedido foi entregue!");
    }

    @Override
    public String toString() {
        return "Venda {" +
                "ID=" + id +
                ", Cliente=" + cliente.getNome() +
                ", Status='" + status + '\'' +
                ", Valor Total=" + calcularValorTotal() +
                ", Itens=" + itens +
                '}';
    }


}
