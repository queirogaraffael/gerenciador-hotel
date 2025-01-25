package org.gerenciador_hotel.ui.reservas;

import javax.swing.*;

public class MenuReservasControllerView {

    private static final String MENU_TITLE = "Gerenciador de Reservas";
    private static final String MENU_MESSAGE = "Escolha uma opcao: ";


    private MenuReservasControllerView() {
    }

    private static final Object[] opcoes = {
            "Criar Reserva",
            "Cancelar Reserva",
            "Voltar"
    };

    public static int exibirMenuGerenciadorReservasView() {
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
