package org.gerenciador_hotel.views.quartos;

import javax.swing.*;

public class MenuManutencaoQuartoView {

    private static final String MENU_TITLE = "Manutencao de Quartos";
    private static final String MENU_MESSAGE = "Escolha uma opcao: ";


    private MenuManutencaoQuartoView() {
    }


    private static final Object[] menuOptions = {
            "Quartos em manutencao",
            "Colocar em manutencao",
            "Retirar manutencao",
            "Voltar"
    };


    public static int exibirMenuManutencaoQuartosView() {
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
