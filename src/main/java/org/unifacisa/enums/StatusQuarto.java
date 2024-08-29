package org.unifacisa.enums;

public enum StatusQuarto {

    DISPONIVEL(0,"Disponivel"),
    OCUPADO(1,"Ocupado"),
    MANUTENCAO(2,"Manutencao");

    private final int codigo;
    private final String descricao;

    StatusQuarto(int codigo, String descricao) {
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

    public static StatusQuarto fromCodigo(int codigo) {
        for (StatusQuarto statusQuarto : StatusQuarto.values()) {
            if (statusQuarto.getCodigo() == codigo) {
                return statusQuarto;
            }
        }
        throw new IllegalArgumentException("Código de prioridade inválido: " + codigo);
    }

}
