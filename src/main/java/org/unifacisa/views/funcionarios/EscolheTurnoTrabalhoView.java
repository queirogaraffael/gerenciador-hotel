package org.unifacisa.views.funcionarios;

import org.unifacisa.enums.Turno;

import javax.swing.*;

public class EscolheTurnoTrabalhoView {

    private static final Object[] opcoes = {"Diurno", "Noturno"};

    private EscolheTurnoTrabalhoView() {
    }

    public static Turno exibeEEscolheTurnoView() {
        int escolha = JOptionPane.showOptionDialog(
                null,
                "Escolha o turno:",
                "Menu escolha",
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
