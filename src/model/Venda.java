package model;

import service.NotificacaoService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private static int contadorId = 1;

    private final int id;
    private final Cliente cliente;
    private final List<ItemVenda> itens;
    private StatusVenda status;
    private final LocalDateTime dataCriacao;
    private CupomDeDesconto cupom;

    public Venda(Cliente cliente) {
        this.id = contadorId++;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.status = StatusVenda.ABERTO;
        this.dataCriacao = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public StatusVenda getStatus() {
        return status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setStatus(StatusVenda status) {
        this.status = status;
    }

    public void adicionarItem(Produto produto, int quantidade, double valorVenda) {
        if (status != StatusVenda.ABERTO) {
            throw new IllegalStateException("Pedido não está aberto para adicionar itens!");
        }
        itens.add(new ItemVenda(produto, quantidade, valorVenda));
    }

    public void aplicarCupom(CupomDeDesconto cupom) {
        if (!cupom.isValido()) {
            throw new IllegalArgumentException("Cupom inválido ou expirado!");
        }
        this.cupom = cupom;
        cupom.marcarComoUtilizado();
        System.out.println("Cupom aplicado com sucesso: " + cupom.getCodigo());
    }

    public double calcularValorTotal() {
        double total = itens.stream()
                .mapToDouble(ItemVenda::calcularSubtotal)
                .sum();
        if (cupom != null && cupom.isValido()) {
            total -= total * (cupom.getPercentualDesconto() / 100);
        }
        return total;
    }

    public void finalizar(NotificacaoService notificacaoService) {
        if (itens.isEmpty() || calcularValorTotal() <= 0) {
            throw new IllegalStateException("Pedido inválido! Não pode ser finalizado.");
        }
        this.status = StatusVenda.AGUARDANDO_PAGAMENTO;
        notificacaoService.notificar(cliente, "Seu pedido foi finalizado. Aguardando pagamento.");
    }

    public void entregar(NotificacaoService notificacaoService) {
        if (status != StatusVenda.PAGO) {
            throw new IllegalStateException("Pedido não pago, não pode ser realizado a entrega!");
        }
        this.status = StatusVenda.FINALIZADO;
        notificacaoService.notificar(cliente, "Seu pedido foi entregue!");
    }

    @Override
    public String toString() {
        return "Venda {" +
                "ID=" + id +
                ", Cliente=" + cliente.getNome() +
                ", Status='" + status + '\'' +
                ", Valor Total=" + calcularValorTotal() +
                ", Data de Criação=" + dataCriacao +
                ", Itens=" + itens +
                '}';
    }


}
