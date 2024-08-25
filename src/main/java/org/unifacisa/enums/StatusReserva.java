package org.unifacisa.enums;

public enum StatusReserva {

    AGENDADO(0, "Agendado"),
    CANCELADO(1, "Cancelado"),
    EM_USO(2, "Em uso"),
    FINALIZADO(3, "Finalizado");


    private final int codigo;
    private final String descricao;

    StatusReserva(int codigo, String descricao) {
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

    public static StatusReserva fromCodigo(int codigo) {
        for (StatusReserva statusReserva : StatusReserva.values()) {
            if (statusReserva.getCodigo() == codigo) {
                return statusReserva;
            }
        }
        throw new IllegalArgumentException("Código de prioridade inválido: " + codigo);
    }

}
