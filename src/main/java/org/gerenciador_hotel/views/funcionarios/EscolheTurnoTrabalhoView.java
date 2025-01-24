package org.gerenciador_hotel.views.funcionarios;

import org.gerenciador_hotel.enums.Turno;

import javax.swing.*;

public class EscolheTurnoTrabalhoView {

    private static final String MENU_TITLE = "Turno";
    private static final String MENU_MESSAGE = "Escolha o turno:";


    private static final Object[] opcoes = {"Diurno", "Noturno"};

    private EscolheTurnoTrabalhoView() {
    }

    public static Turno exibeEEscolheTurnoView() {
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
            return Turno.DIURNO;
        } else {
            return Turno.NOTURNO;
        }


    }
}
