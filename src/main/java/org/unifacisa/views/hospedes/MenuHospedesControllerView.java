package org.unifacisa.views.hospedes;

import javax.swing.*;

public class MenuHospedesControllerView {

    private static final String MENU_TITLE = "Gerenciador de Hospedes";
    private static final String MENU_PROMPT = "Escolha uma opcao: ";

    private MenuHospedesControllerView() {
    }

    private static final Object[] menuOptions = {
            "Cadastrar",
            "Visualizar Hospede Pelo CPF",
            "Atualizar Dados Hospede",

            "Buscar e Visualizar Reserva de Hospede", // reservas // dados basicos da entidade reserva


            "Buscar e Visualizar Historico de Hospede", // dto // check in/ouHISTORICO ENTIDADE

            "Check-in/out", //
            "Voltar"

    };


    public static String exibirMenuTarefasView() {
        Object opcaoSelecionada = JOptionPane.showInputDialog(
                null,
                MENU_PROMPT,
                MENU_TITLE,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                menuOptions,
                menuOptions[0]
        );

        if (opcaoSelecionada != null) {
            return opcaoSelecionada.toString();
        }

        return menuOptions[menuOptions.length-1].toString();
    }
}
