package org.unifacisa.views;

import javax.swing.*;

public class MenuPrincipalControllerView {

    public MenuPrincipalControllerView() {
    }

    private static final Object[] opcoes = {"Quartos", "Hospedes ", "Reservas", "Funcionarios", "Check-In/Out", "Encerrar programa"};

    public static int exibeViewMenuPrincipal() {
        return JOptionPane.showOptionDialog(
                null,
                "Escolha um Gerenciador: ",
                "Gerenciador Hotel",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

    }


}
