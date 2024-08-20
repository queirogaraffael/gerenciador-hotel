package org.unifacisa.enums;

public enum Turno {

    DIURNO(0, "Diurno"),
    NOTURNO(1, "Noturno");

    private final int codigo;
    private final String descricao;

    Turno(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Turno porCodigo(int codigo) {
        for (Turno turno : Turno.values()) {
            if (turno.getCodigo() == codigo) {
                return turno;
            }
        }
        throw new IllegalArgumentException("Codigo de turno invalido: " + codigo);
    }

    @Override
    public String toString() {
        return descricao;
    }
}
