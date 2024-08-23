package org.unifacisa.utils;

public class VerificaCPF {

    private static final String cpfPadraoRegex = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
    public static final String padraoCPF = "XXX.XXX.XXX-XX";

    public static boolean isCpfValido(String cpf) {

        if (cpf.matches(cpfPadraoRegex)) {
            return true;
        } else {
            return false;
        }
    }
}
