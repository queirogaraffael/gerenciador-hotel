package org.gerenciador_hotel.views.quartos;

import org.gerenciador_hotel.enums.TipoQuarto;

import javax.swing.*;

public class EscolheTipoQuartoView {

    private static final String MENU_TITLE = "Tipo de Quarto";
    private static final String MENU_MESSAGE = "Escolha um tipo:";

    private static final Object[] opcoes = {"Solteiro", "Casal", "Suite"};

    private EscolheTipoQuartoView() {
    }

    public static TipoQuarto exibeEEscolheTipoQuartoView() {
        int escolha = JOptionPane.showOptionDialog(
                null,
                MENU_MESSAGE,
                MENU_TITLE,
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
