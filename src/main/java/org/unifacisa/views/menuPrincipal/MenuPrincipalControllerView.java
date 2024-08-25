package org.unifacisa.views.menuPrincipal;

import javax.swing.*;

public class MenuPrincipalControllerView {

    private static final String MENU_TITLE = "Gerenciador Hotel";
    private static final String MENU_MESSAGE = "Escolha um Gerenciador: ";

    private MenuPrincipalControllerView() {
    }

    private static final Object[] opcoes = {"Quartos", "Hospedes ", "Reservas", "Funcionarios", "Check-In/Out", "Encerrar programa"};

    public static int exibeViewMenuPrincipal() {
        return JOptionPane.showOptionDialog(
                null,
                MENU_MESSAGE,
                MENU_TITLE,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

    }


}
