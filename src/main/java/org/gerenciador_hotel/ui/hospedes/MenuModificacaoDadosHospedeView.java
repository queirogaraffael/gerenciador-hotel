package org.gerenciador_hotel.ui.hospedes;

import javax.swing.*;

public class MenuModificacaoDadosHospedeView {

    private static final String MENU_TITLE = "Modificar Hospede";
    private static final String MENU_MESSAGE = "Escolha uma opcao: ";

    private static final Object[] opcoesModificar = {"Nome", "Numero telefone", "Endereco", "Voltar"};

    private MenuModificacaoDadosHospedeView() {
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
