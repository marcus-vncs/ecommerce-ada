package model;

public enum StatusVenda {
    ABERTO,
    AGUARDANDO_PAGAMENTO,
    PAGO,
    FINALIZADO;

    @Override
    public String toString() {
        switch (this) {
            case ABERTO: return "Aberto";
            case AGUARDANDO_PAGAMENTO: return "Aguardando pagamento";
            case PAGO: return "Pago";
            case FINALIZADO: return "Finalizado";
            default: return super.toString();
        }
    }
}
