package org.unifacisa.enums;

public enum TipoQuarto {

    SOLTEIRO(0, "Solteiro"),
    CASAL(1, "Casal"),
    SUITE(2, "Suite");

    private final int codigo;
    private final String descricao;

    TipoQuarto(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }

    public static TipoQuarto fromCodigo(int codigo) {
        for (TipoQuarto tipoQuarto : TipoQuarto.values()) {
            if (tipoQuarto.getCodigo() == codigo) {
                return tipoQuarto;
            }
        }
        throw new IllegalArgumentException("Código de prioridade inválido: " + codigo);
    }
}
