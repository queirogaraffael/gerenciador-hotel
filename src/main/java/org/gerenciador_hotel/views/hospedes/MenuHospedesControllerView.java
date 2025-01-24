package org.gerenciador_hotel.views.hospedes;

import javax.swing.*;

public class MenuHospedesControllerView {

    private static final String MENU_TITLE = "Gerenciador de Hospedes";
    private static final String MENU_MESSAGE = "Escolha uma opcao: ";

    private MenuHospedesControllerView() {
    }

    private static final Object[] menuOptions = {
            "Cadastrar",
            "Ver",
            "Ver Reservas",
            "Ver Historico",
            "Atualizar",
            "Voltar"
    };


    public static int exibirMenuTarefasView() {
        return JOptionPane.showOptionDialog(
                null,
                MENU_MESSAGE,
                MENU_TITLE,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                menuOptions,
                menuOptions[0]
        );
    }
}
