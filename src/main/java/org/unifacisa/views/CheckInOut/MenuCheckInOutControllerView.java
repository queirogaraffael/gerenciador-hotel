package org.unifacisa.views.CheckInOut;

import javax.swing.*;

public class MenuCheckInOutControllerView {

    private static final String MENU_TITLE = "Check-In/Out";
    private static final String MENU_PROMPT = "Escolha uma opcao: ";

    private static final Object[] opcoes = {"Check-In", "Check-Out", "Voltar"};

    public static int exibeViewMenuCheckInOut() {
        return JOptionPane.showOptionDialog(
                null,
                MENU_PROMPT,
                MENU_TITLE,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

    }



}
