package service;

import model.Cliente;

public interface NotificacaoService {
    void notificar(Cliente cliente, String mensagem);
}
