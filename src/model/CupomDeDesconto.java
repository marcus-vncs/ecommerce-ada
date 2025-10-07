package model;

import java.time.LocalDateTime;

public class CupomDeDesconto {
    private final String codigo;
    private double percentualDesconto;
    private LocalDateTime dataExpiracao;
    private boolean utilizado;

    public CupomDeDesconto(String codigo, double percentualDesconto, LocalDateTime dataExpiracao) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Código do cupom é obrigatório!");
        }
        if (percentualDesconto <= 0 || percentualDesconto > 100) {
            throw new IllegalArgumentException("Percentual inválido!");
        }

        this.codigo = codigo;
        this.percentualDesconto = percentualDesconto;
        this.dataExpiracao = dataExpiracao;
        this.utilizado = false;
    }

    public boolean isValido() {
        return !utilizado && LocalDateTime.now().isBefore(dataExpiracao);
    }

    public void marcarComoUtilizado() {
        this.utilizado = true;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getPercentualDesconto() {
        return percentualDesconto;
    }

    public LocalDateTime getDataExpiracao() {
        return dataExpiracao;
    }

    public boolean isUtilizado() {
        return utilizado;
    }

    @Override
    public String toString() {
        return "Cupom {" +
                "Código='" + codigo + '\'' +
                ", Desconto=" + percentualDesconto + "%" +
                ", Expira em=" + dataExpiracao +
                ", Utilizado=" + (utilizado ? "Sim" : "Não") +
                '}';
    }
}