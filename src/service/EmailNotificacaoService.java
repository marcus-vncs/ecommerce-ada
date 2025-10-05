package service;

import model.Cliente;

public class EmailNotificacaoService implements NotificacaoService {
    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("📧 Enviando e-mail para " + cliente.getNome() + ": " + mensagem);
    }
}
