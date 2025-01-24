package org.gerenciador_hotel.utils;

public class VerificaCPF {

    private VerificaCPF() {
    }

    private static final String CPF_PADRAO_REGEX = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
    public static final String PADRAO_CPF = "XXX.XXX.XXX-XX";

    public static boolean isCpfValido(String cpf) {
        return cpf.matches(CPF_PADRAO_REGEX);
    }
}
