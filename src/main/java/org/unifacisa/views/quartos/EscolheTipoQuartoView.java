package org.unifacisa.views.quartos;

import org.unifacisa.enums.TipoQuarto;

import javax.swing.*;

public class EscolheTipoQuartoView {

    private static final Object[] opcoes = {"Solteiro", "Casal", "Suite"};

    private EscolheTipoQuartoView() {
    }

    public static TipoQuarto exibeEEscolheTipoQuartoView() {
        int escolha = JOptionPane.showOptionDialog(
                null,
                "Escolha tipo quarto:",
                "Menu escolha",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

        if (escolha == 0) {
            return TipoQuarto.SOLTEIRO;
        } else if (escolha == 1) {
            return TipoQuarto.CASAL;
        } else {
            return TipoQuarto.SUITE;
        }

    }
}
