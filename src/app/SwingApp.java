package app;

import model.*;
import repository.*;
import service.*;

import javax.swing.*;

public class SwingApp {
    public static void main(String[] args) {
        // Serviços
        NotificacaoService notificacaoService = new EmailNotificacaoService();
        PagamentoService pagamentoService = new PagamentoServiceImpl(notificacaoService);

        // Repositórios
        ClienteRepository clienteRepo = new ClienteRepositoryImpl();
        ProdutoRepository produtoRepo = new ProdutoRepositoryImpl();
        VendaRepository vendaRepo = new VendaRepositoryImpl();

        while (true) {
            String[] opcoes = {
                    "Cadastrar Cliente",
                    "Cadastrar Produto",
                    "Criar Venda",
                    "Finalizar Venda",
                    "Pagar Venda",
                    "Entregar Venda",
                    "Exibir Clientes",
                    "Exibir Produtos",
                    "Exibir Vendas",
                    "Sair"
            };

            String escolha = (String) JOptionPane.showInputDialog(
                    null,
                    "Selecione uma opção:",
                    "Ada Commerce",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            if (escolha == null || escolha.equals("Sair")) break;

            try {
                switch (escolha) {
                    case "Cadastrar Cliente":
                        String nome = JOptionPane.showInputDialog("Nome do cliente:");
                        String doc = JOptionPane.showInputDialog("Documento do cliente:");
                        clienteRepo.salvar(new Cliente(nome, doc));
                        break;

                    case "Cadastrar Produto":
                        int id = Integer.parseInt(JOptionPane.showInputDialog("ID do produto:"));
                        String nomeProduto = JOptionPane.showInputDialog("Nome do produto:");
                        double preco = Double.parseDouble(JOptionPane.showInputDialog("Preço do produto:"));
                        produtoRepo.salvar(new Produto(id, nomeProduto, preco));
                        break;

                    case "Criar Venda":
                        String docCliente = JOptionPane.showInputDialog("Documento do cliente da venda:");
                        Cliente cliente = clienteRepo.buscarPorDocumento(docCliente);
                        if (cliente == null) {
                            JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
                            break;
                        }
                        Venda venda = new Venda(cliente);

                        int idProduto = Integer.parseInt(JOptionPane.showInputDialog("ID do produto:"));
                        Produto produto = produtoRepo.buscarPorId(idProduto);
                        if (produto == null) {
                            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
                            break;
                        }
                        int qtd = Integer.parseInt(JOptionPane.showInputDialog("Quantidade:"));
                        double valorVenda = Double.parseDouble(JOptionPane.showInputDialog("Valor unitário da venda:"));

                        venda.adicionarItem(produto, qtd, valorVenda);
                        vendaRepo.salvar(venda);
                        JOptionPane.showMessageDialog(null, "Venda criada: " + venda);
                        break;

                    case "Finalizar Venda":
                        int idVendaFinalizar = Integer.parseInt(JOptionPane.showInputDialog("ID da venda:"));
                        Venda vendaFinalizar = vendaRepo.buscarPorId(idVendaFinalizar);
                        if (vendaFinalizar != null) {
                            vendaFinalizar.finalizar(notificacaoService);
                        }
                        break;

                    case "Pagar Venda":
                        int idVendaPagar = Integer.parseInt(JOptionPane.showInputDialog("ID da venda:"));
                        Venda vendaPagar = vendaRepo.buscarPorId(idVendaPagar);
                        if (vendaPagar != null) {
                            double valorPago = Double.parseDouble(JOptionPane.showInputDialog("Valor do pagamento:"));
                            pagamentoService.pagar(vendaPagar, valorPago);
                        }
                        break;

                    case "Entregar Venda":
                        int idVendaEntregar = Integer.parseInt(JOptionPane.showInputDialog("ID da venda:"));
                        Venda vendaEntregar = vendaRepo.buscarPorId(idVendaEntregar);
                        if (vendaEntregar != null) {
                            vendaEntregar.entregar(notificacaoService);
                        }
                        break;

                    case "Exibir Clientes":
                        clienteRepo.exibirTodos();
                        break;

                    case "Exibir Produtos":
                        produtoRepo.exibirTodos();
                        break;

                    case "Exibir Vendas":
                        vendaRepo.exibirTodos();
                        break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        }

        JOptionPane.showMessageDialog(null, "Sistema encerrado!");
    }
}
