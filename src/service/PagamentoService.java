package service;

import model.Venda;

public interface PagamentoService {
    boolean pagar(Venda venda, double valor);
}
