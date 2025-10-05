package app;

import model.Cliente;
import model.Produto;
import model.Venda;
import repository.*;
import service.EmailNotificacaoService;
import service.NotificacaoService;
import service.PagamentoService;
import service.PagamentoServiceImpl;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //ClienteRepository clienteRepository = new ClienteRepositoryImpl();

        // Cadastro cliente
//        model.Cliente c1 = new model.Cliente("Marcus Vinicius", "123564897");
//        model.Cliente c2 = new model.Cliente("Silvia", "451298839");
//        model.Cliente c3 = new model.Cliente("Marcus Vinicius", "123564897");

        // Salvar no repositório
//        clienteRepository.salvar(c1);
//        clienteRepository.salvar(c2);
//        clienteRepository.salvar(c3);

        // Listando clientes
//        System.out.println("\n------------- " + " primeira exibição " + "------------- ");
//        System.out.println("=== Lista de Clientes ===");
//        clienteRepository.exibirTodos();

        // Atualizando cliente
        //System.out.println("\n------------- " + " segunda exibição " + "------------- ");
        //clienteRepository.atualizar("451298839", "Silvia Lopez");

        //clienteRepository.exibirTodos();

        // --------------------------------------------------------------------

        //listagem de Produtos
//        System.out.println("\n------------- " + " Listagem Produtos " + "------------- ");
//
//        IProdutoRepository produtoRepository = new ProdutoRepository();
//
//        model.Produto p1 = new model.Produto("P001", "Notebook", 3500.00, 10);
//        model.Produto p2 = new model.Produto("P002", "Mouse", 50.00, 100);
//        model.Produto p3 = new model.Produto("P001", "Outro Notebook", 3700.00, 5); // ID duplicado
//
//        produtoRepository.salvar(p1);
//        produtoRepository.salvar(p2);
//        produtoRepository.salvar(p3); // não será salvo, id duplicado
//
//        produtoRepository.exibirTodos();
//
//        produtoRepository.atualizar("P001", "Notebook Gamer", 3600.00, 8);
//        //produtoRepository.atualizar("P999", "Teclado", 120.00, 50);
//
//        //listagem atualizada
//        System.out.println("\n------------- " + " Segunda exibição " + "------------- ");
//        produtoRepository.exibirTodos();

        // --------------------------------------------------------------------

        // Serviços
        NotificacaoService notificacaoService = new EmailNotificacaoService();
        PagamentoService pagamentoService = new PagamentoServiceImpl(notificacaoService);

        // Repositórios
        ClienteRepository clienteRepo = new ClienteRepositoryImpl();
        ProdutoRepository produtoRepo = new ProdutoRepositoryImpl();
        VendaRepository vendaRepo = new VendaRepositoryImpl();

        // -------------------------
        // 1. Cadastro de clientes
        // -------------------------
        Cliente c1 = new Cliente("Marcus", "111.111.111-11");
        Cliente c2 = new Cliente("Silvia", "222.222.222-22");

        clienteRepo.salvar(c1);
        clienteRepo.salvar(c2);
        clienteRepo.exibirTodos();

        // -------------------------
        // 2. Cadastro de produtos
        // -------------------------
        Produto p1 = new Produto(1, "Notebook", 3500.0);
        Produto p2 = new Produto(2, "Mouse", 120.0);
        Produto p3 = new Produto(3, "Teclado", 220.0);

        produtoRepo.salvar(p1);
        produtoRepo.salvar(p2);
        produtoRepo.salvar(p3);
        produtoRepo.exibirTodos();

        // -------------------------
        // 3. Criação de uma venda
        // -------------------------
        Venda venda1 = new Venda(c1);
        venda1.adicionarItem(p1, 1, 3400.0); // desconto aplicado
        venda1.adicionarItem(p2, 2, 100.0);  // preço diferente do catálogo

        vendaRepo.salvar(venda1);
        vendaRepo.exibirTodos();

        // -------------------------
        // 4. Finalizar venda
        // -------------------------
        venda1.finalizar(notificacaoService);
        System.out.println("Status da venda após finalização: " + venda1.getStatus());

        // -------------------------
        // 5. Realizar pagamento
        // -------------------------
        pagamentoService.pagar(venda1, 3600.0);
        System.out.println("Status da venda após pagamento: " + venda1.getStatus());

        // -------------------------
        // 6. Entregar venda
        // -------------------------
        venda1.entregar(notificacaoService);
        System.out.println("Status da venda após entrega: " + venda1.getStatus());

        // -------------------------
        // 7. Exibir vendas no sistema
        // -------------------------
        vendaRepo.exibirTodos();
    }

}