package org.gerenciador_hotel.ui.funcionarios;

import javax.swing.*;

public class MenuFuncionarioControllerView {

    private static final String MENU_TITLE = "Gerenciador de Funcionarios";
    private static final String MENU_MESSAGE = "Escolha uma opcao: ";

    private MenuFuncionarioControllerView() {
    }


    private static final Object[] menuOptions = {
            "Cadastrar",
            "Editar",
            "Buscar por CPF",
            "Buscar por Nome",
            "Extratos",
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
