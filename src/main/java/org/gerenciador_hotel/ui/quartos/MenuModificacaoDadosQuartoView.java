package org.gerenciador_hotel.ui.quartos;

import javax.swing.*;

public class MenuModificacaoDadosQuartoView {

    private static final String MENU_TITLE = "Modificar dados do quarto";
    private static final String MENU_MESSAGE = "Escolha uma opcao: ";

    private static final Object[] opcoesModificar = {"Tipo", "Preco", "Capacidade", "Voltar"};

    private MenuModificacaoDadosQuartoView() {
    }

    public static int exibeOpcoesModificarDadosView() {
        return JOptionPane.showOptionDialog(
                null,
                MENU_MESSAGE,
                MENU_TITLE,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoesModificar,
                opcoesModificar[0]
        );

    }


}
