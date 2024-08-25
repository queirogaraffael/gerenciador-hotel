package org.unifacisa.views.funcionarios;

import javax.swing.*;

public class MenuModificacaoDadosFuncionarioView {

    private static final String MENU_TITLE = "Modificar dados do funcionario";
    private static final String MENU_MESSAGE = "Escolha uma opcao: ";

    private static final Object[] opcoesModificar = {"Nome", "Telefone", "Turno", "Cargo", "Endereco", "Voltar"};

    private MenuModificacaoDadosFuncionarioView() {
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
