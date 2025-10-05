package model;

public class Cliente {
    private String nome;
    private final String documento;

    public Cliente(String nome, String documento) {
        if (documento == null || documento.isBlank()) {
            throw new IllegalArgumentException("O campo documento é obrigatório!");
        }
        this.nome = nome;
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "Nome='" + nome + '\'' +
                ", Documento='" + documento + '\'' +
                '}';
    }
}
