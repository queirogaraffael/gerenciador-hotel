package org.unifacisa.views.quartos;

import javax.swing.*;

public class MenuQuartosControllerView {
    private static final String MENU_TITLE = "Gerenciador de Quartos";
    private static final String MENU_PROMPT = "Escolha uma opcao: ";


    private MenuQuartosControllerView() {
    }

    private static final Object[] menuOptions = {
            "Cadastrar",
            "Visualizar Quarto(s) Por Tipo",
            "Visualizar Quarto(s) Por Tipo Disponiveis Por Data",  //
            "Atualizar Dados do Quarto",
            "Voltar"
    };


    public static String exibirMenuGerenciadorQuartosView() {
        Object opcao = JOptionPane.showInputDialog(
                null,
                MENU_PROMPT,
                MENU_TITLE,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                menuOptions,
                menuOptions[0]
        );

        if (opcao != null) {
            return opcao.toString();
        }

        return menuOptions[menuOptions.length-1].toString();

    }
}
