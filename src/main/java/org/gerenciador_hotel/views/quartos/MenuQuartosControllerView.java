package org.gerenciador_hotel.views.quartos;

import javax.swing.*;

public class MenuQuartosControllerView {

    private static final String MENU_TITLE = "Gerenciador de Quartos";
    private static final String MENU_MESSAGE = "Escolha uma opcao: ";


    private MenuQuartosControllerView() {
    }

    private static final Object[] menuOptions = {
            "Cadastrar",
            "Ver por Tipo",
            "Ver Disp. por Data",
            "Atualizar Dados",
            "Manutencao",
            "Voltar"
    };


    public static int exibirMenuGerenciadorQuartosView() {
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
