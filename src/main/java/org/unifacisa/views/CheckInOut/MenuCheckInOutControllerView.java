package org.unifacisa.views.CheckInOut;

import javax.swing.*;

public class MenuCheckInOutControllerView {

    private static final Object[] opcoes = {"Check-In", "Check-Out", "Voltar"};

    public static int exibeViewMenuCheckInOut() {
        return JOptionPane.showOptionDialog(
                null,
                "Escolha um: ",
                "Check-In/Out",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

    }



}
