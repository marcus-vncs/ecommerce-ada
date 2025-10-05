package model;

public class ItemVenda {
    private final Produto produto;
    private final int quantidade;
    private final double valorVenda; // valor aplicado nesta venda (pode ser diferente do catálogo)

    public ItemVenda(Produto produto, int quantidade, double valorVenda) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorVenda = valorVenda;
    }

    public double calcularSubtotal() {
        return quantidade * valorVenda;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    @Override
    public String toString() {
        return "ItemVenda{" +
                "Produto='" + produto.getNome() + '\'' +
                ", Quantidade=" + quantidade +
                ", Valor Unitário=" + valorVenda +
                ", Subtotal=" + calcularSubtotal() +
                '}';
    }
}
