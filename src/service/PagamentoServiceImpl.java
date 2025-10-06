package service;

import model.StatusVenda;
import model.Venda;

public class PagamentoServiceImpl implements PagamentoService {
    private final NotificacaoService notificacaoService;

    public PagamentoServiceImpl(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @Override
    public boolean pagar(Venda venda, double valor) {
        if (venda.getStatus() != StatusVenda.AGUARDANDO_PAGAMENTO) {
            throw new IllegalStateException("Venda não está aguardando pagamento!");
        }

        if (valor < venda.calcularValorTotal()) {
            throw new IllegalArgumentException("Valor insuficiente para pagamento!");
        }

        venda.setStatus(StatusVenda.PAGO);
        notificacaoService.notificar(venda.getCliente(), "Pagamento recebido com sucesso!");
        return true;
    }
}
